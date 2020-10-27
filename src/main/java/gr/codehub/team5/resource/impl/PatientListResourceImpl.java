package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.PatientListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class PatientListResourceImpl extends ServerResource implements PatientListResource  {
    private EntityManager em;
    PatientRepository patientRepository;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public PatientRepresentation addPatient(PatientRepresentation patientRepresentation) throws BadEntityException {
        if (patientRepresentation == null) throw new BadEntityException("Null patient error");
        Patient patient = PatientRepresentation.getPatient(patientRepresentation);
        patientRepository.save(patient);
        return PatientRepresentation.getPatientRepresentation(patient);
    }

}
