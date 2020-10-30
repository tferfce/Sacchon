package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.ConsultationsResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsultationsResourceImpl extends ServerResource implements ConsultationsResource {

    private ConsultationRepository consultationRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private EntityManager em;
    private long  doctorId;
    private long patientId;



    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
            doctorRepository = new DoctorRepository(em);
            patientRepository = new PatientRepository(em);
            //doctorId = Long.parseLong(getAttribute("doctorId"));
            //patientId = Long.parseLong(getAttribute("patientId"));
        }
        catch(Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException, NotFoundException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_DOCTOR.getRoleName());

        if (consultationIn==null) throw new  BadEntityException("Null consultation representation error");

        Optional<Doctor> doctorOpt = doctorRepository.findById(consultationIn.getDoctorId());
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given doctor id is not existing");
        Doctor doctor = doctorOpt.get();
        if (!doctor.isActive()) throw new NotFoundException("Inactive Doctor");
        Optional<Patient> patientOpt = patientRepository.findById(consultationIn.getPatientId());
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given patient id is not existing");
        Patient patient = patientOpt.get();

        Consultations consultation = ConsultationRepresentation.getConsultation(consultationIn);
        consultation.setDocId(doctor);
        consultation.setPatId(patient);
        consultation.setDate(new Date());
        patient.setDoctor(doctor);
        patientRepository.save(patient);
        consultationRepository.save(consultation);
        return ConsultationRepresentation.getConsultationRepresentation(consultation);
    }

    @Override
    public List<ConsultationRepresentation> getConsultations() throws NotFoundException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_DOCTOR.getRoleName());
        List<Consultations> consultations= consultationRepository.findAll();

        List<ConsultationRepresentation> consultationRepresentationList = new ArrayList<>();

        consultations.forEach(consultation -> consultationRepresentationList.add(ConsultationRepresentation.getConsultationRepresentation(consultation)));

        return consultationRepresentationList;

    }
}
