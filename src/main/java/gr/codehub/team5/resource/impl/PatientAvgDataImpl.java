package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.resource.PatientAvgData;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientAvgDataImpl extends ServerResource implements PatientAvgData {

    private EntityManager em;
    private long id;

    public PatientAvgDataImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
        id = Long.parseLong(getAttribute("id"));
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public double[] getAvgData() throws NotFoundException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        if (pdataList.isEmpty()) throw new NotFoundException("No data");
        double totalCarbs=0;
        double totalBloodGlucose = 0;
        for (PatientData pdata: pdataList){
            totalCarbs += pdata.getCarbIntake();
            totalBloodGlucose +=pdata.getBloodGlucose();
        }
        double[] avgStatistics = new double[2];
        avgStatistics[0] = totalCarbs/pdataList.size();
        avgStatistics[1] = totalBloodGlucose/pdataList.size();
        return avgStatistics;
    }
}
