package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.resource.PatientDataSpecifyResource;
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
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        patientDataRepository.deleteById(pdataList.get(listId).getId());
    }
}
