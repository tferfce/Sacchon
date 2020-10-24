package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.util.PatientToDoctorResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class PatientToDoctorResourceImpl extends ServerResource implements PatientToDoctorResource {

    private EntityManager em;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private long doctorId;
    private long patientId;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
            doctorRepository= new DoctorRepository(em);
            doctorId=Long.parseLong(getAttribute("doctorId"));
            patientId=Long.parseLong(getAttribute("patientId"));
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public PatientRepresentation setDoctorToPatient() throws  ResourceException {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor= doctorRepository.findById(doctorId);

        patient.get().setDoctor(doctor.get());

        patientRepository.save(patient.get());
        return PatientRepresentation.getPatientRepresentation(patient.get());


    }

}
