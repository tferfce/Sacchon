package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.GetNewPatients;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class GetNewPatientsImpl extends ServerResource implements GetNewPatients {

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
    public List<PatientRepresentation> getNewPatients() throws NotFoundException {
        TypedQuery<Patient> query = em.createQuery("FROM Patient P WHERE doctor_id is null", Patient.class);
        List<Patient> patients = query.getResultList();
        if (patients.isEmpty()) throw new NotFoundException("No Patients");
        List<PatientRepresentation> patientRepresentations = new ArrayList<>();
        patients.forEach(patient -> patientRepresentations.add(PatientRepresentation.getPatientRepresentation(patient)));
        return patientRepresentations;
    }
}
