package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.resource.util.PatientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class PatientResourceImpl extends ServerResource implements PatientResource {
    private EntityManager em;
    private PatientRepository patientRepository;
    private long id;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
            id=Long.parseLong(getAttribute("id"));
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public Patient getPatient() throws NotFoundException, ResourceException {
        Patient patient = patientRepository.findById(id).get();
        return patient;
    }
}
