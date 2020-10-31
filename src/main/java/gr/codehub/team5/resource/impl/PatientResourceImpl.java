package gr.codehub.team5.resource.impl;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.PatientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.util.Optional;

public class PatientResourceImpl extends ServerResource implements PatientResource {
    private EntityManager em;
    private PatientRepository patientRepository;
    private PatientDataRepository patientDataRepository;
    private long id;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
            patientDataRepository = new PatientDataRepository(em);
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
    public PatientRepresentation getPatient() throws NotFoundException, ResourceException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_DOCTOR.getRoleName());
        Optional<Patient> patient = patientRepository.findById(id);
        setExisting(patient.isPresent());
        if (!patient.isPresent()) throw new NotFoundException("Patient is not found");
        PatientRepresentation patientRepresentation = PatientRepresentation.getPatientRepresentation(patient.get());
        return patientRepresentation;
    }
    @Override
    public PatientRepresentation updatePatient(PatientRepresentation patientRepresentation) throws NotFoundException, BadEntityException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) throw new NotFoundException("No such patient exists");
        Patient patient = patientOptional.get();
        patient.setFirstName(patientRepresentation.getFirstName());
        patient.setLastName(patientRepresentation.getLastName());
        patient.setPassword(patientRepresentation.getPassword());

        patientRepository.save(patient);
        return PatientRepresentation.getPatientRepresentation(patient);

    }

    @Override
    public PatientDataRepresentation addPatientData(PatientData patientData) throws BadEntityException, NotFoundException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (!patientOpt.isPresent()) throw new NotFoundException("No such patient exists");
        Patient patient = patientOpt.get();
        if (patientData == null) throw new BadEntityException("Null Entity");
        patientData.setPData(patient);
        patientDataRepository.save(patientData);
        return PatientDataRepresentation.getDataRepresentation(patientData);
    }

    @Override
    public void deletePatient() throws NotFoundException {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (!patientOpt.isPresent()) throw new NotFoundException("No such patient exists");
        patientOpt.get().setActive(false);
        patientRepository.save(patientOpt.get());
    }
}
