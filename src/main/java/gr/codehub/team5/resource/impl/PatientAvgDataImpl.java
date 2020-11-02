package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.resource.PatientAvgData;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientAvgDataImpl extends ServerResource implements PatientAvgData {

    private EntityManager em;
    private long id;
    private PatientDataRepository patientDataRepository;

    public PatientAvgDataImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            id = Long.parseLong(getAttribute("id"));
            patientDataRepository = new PatientDataRepository(em);
        }catch (ResourceException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public Map<String, Double> getAvgData() throws NotFoundException,ParseException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());

        String paramValue1=getQueryValue("fromDate");
        String paramValue2=getQueryValue("toDate");
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue1);
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue2);
        // C) Add 1 day to toDate
        Calendar c = Calendar.getInstance();
        c.setTime(dateTo);
        c.add(Calendar.DATE, 1);
        dateTo = c.getTime();
        List<PatientData> pdataList = new ArrayList<>();
        List<PatientData> allDataInRange = patientDataRepository.findByTimeRange(dateFrom,dateTo);
        for (PatientData p: allDataInRange){
            if (p.getPData().getId()==this.id){
                pdataList.add(p);
            }
        }
        if (pdataList.isEmpty()) throw new NotFoundException("No data");
        double totalCarbs=0;
        double totalBloodGlucose = 0;
        for (PatientData pdata: pdataList){
            totalCarbs += pdata.getCarbIntake();
            totalBloodGlucose +=pdata.getBloodGlucose();
        }
        Map<String ,Double> avgStatistics = new HashMap();
        avgStatistics.put("avgCarbs",totalCarbs/pdataList.size());
        avgStatistics.put("avgGlycose",totalBloodGlucose/pdataList.size());
        return avgStatistics;
    }
}
