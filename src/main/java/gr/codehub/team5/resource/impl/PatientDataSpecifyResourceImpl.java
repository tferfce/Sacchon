package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.PatientDataSpecifyResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientDataSpecifyResourceImpl extends ServerResource implements PatientDataSpecifyResource {

    private long id;
    private int listId;
    private EntityManager em;
    private PatientDataRepository patientDataRepository;
    private PatientRepository patientRepository;

    public PatientDataSpecifyResourceImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
        id=Long.parseLong(getAttribute("id"));
        listId= Integer.parseInt(getAttribute("listId"));
        patientDataRepository = new PatientDataRepository(em);
        patientRepository = new PatientRepository(em);
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public void deleteSpecificData() throws NotFoundException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        patientDataRepository.deleteById(pdataList.get(listId-1).getId());
    }

    @Override
    public PatientDataRepresentation updatePData(PatientDataRepresentation patientDataRepresentation) throws NotFoundException, BadEntityException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        PatientData patientData = pdataList.get(listId-1);
        patientData.setBloodGlucose(patientDataRepresentation.getBloodGlucose());
        patientData.setCarbIntake(patientDataRepresentation.getCarbIntake());
        patientDataRepository.save(patientData);

        return PatientDataRepresentation.getDataRepresentation(patientData);
    }
}
