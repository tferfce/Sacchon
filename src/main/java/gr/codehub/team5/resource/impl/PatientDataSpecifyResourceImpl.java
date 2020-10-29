package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.PatientDataSpecifyResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientDataSpecifyResourceImpl extends ServerResource implements PatientDataSpecifyResource {

    private long id;
    private EntityManager em;
    private PatientDataRepository patientDataRepository;
    private PatientRepository patientRepository;

    public PatientDataSpecifyResourceImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            id = Long.parseLong(getAttribute("id"));
            patientDataRepository = new PatientDataRepository(em);
            patientRepository = new PatientRepository(em);
        } catch (Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public void deleteSpecificData() throws NotFoundException {
        String paramValue1=getQueryValue("id");
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        if (pdataList.isEmpty()) throw new NotFoundException("No data to delete!");
        for (PatientData p: pdataList){
            if (p.getId()==Long.parseLong(paramValue1)){
                patientDataRepository.deleteById(Long.parseLong(paramValue1));
            }
        }
    }

    @Override
    public PatientDataRepresentation updatePData(PatientDataRepresentation patientDataRepresentation) throws NotFoundException{
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        if (pdataList.isEmpty()) throw new NotFoundException("No data to update");
        String paramValue1=getQueryValue("id");
        for (PatientData p: pdataList){
            if (p.getId()==Long.parseLong(paramValue1)){
                PatientData patientData = patientDataRepository.findById(Long.parseLong(paramValue1)).get();
                patientData.setBloodGlucose(patientDataRepresentation.getBloodGlucose());
                patientData.setCarbIntake(patientDataRepresentation.getCarbIntake());
                patientDataRepository.save(patientData);
                return PatientDataRepresentation.getDataRepresentation(patientData);
            }
        }
        return null;
    }
}
