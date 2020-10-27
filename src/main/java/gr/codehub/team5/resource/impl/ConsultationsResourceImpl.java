package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.util.ConsulationsResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ConsultationsResourceImpl extends ServerResource implements ConsulationsResource {

    private ConsultationRepository consultationRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private EntityManager em;
    private long  doctorId;
    private long patientId;
    private boolean doctorIsReady;


    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
            doctorRepository = new DoctorRepository(em);
            patientRepository = new PatientRepository(em);
            doctorId = Long.parseLong(getAttribute("doctorId"));
            patientId = Long.parseLong(getAttribute("patientId"));
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

        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given doctor id is not existing");
        Doctor doctor = doctorOpt.get();

        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given patient id is not existing");
        Patient patient = patientOpt.get();

        doctorIsReady= checkDoctorStatus(patient, doctor);

        if (doctorIsReady==true){
            Consultations consultation = ConsultationRepresentation.getConsultation(consultationIn);
            consultation.setDocId(doctor);
            consultation.setPatId(patient);
            consultationRepository.save(consultation);
            return ConsultationRepresentation.getConsultationRepresentation(consultation);
        }
        else{
            throw new BadEntityException("The Doctor can not make a consult yet.");
        }

    }

    public boolean checkDoctorStatus(Patient patient, Doctor doctor) throws BadEntityException {
        TypedQuery<Consultations> query2= em.createQuery("FROM Consultations c WHERE docId_id=:param", Consultations.class);
        query2.setParameter("param", doctor.getId());
        List<Consultations> consults= query2.getResultList();
        if (consults.size()==0){
            List<PatientData> pdataList= getQueryResult(patient);
            if (pdataList.size()==0){
                throw new BadEntityException("The patient hasn't entered any data.");
            }
            else{
                Date fromDate= pdataList.get(0).getDate();
                if (calculatediff(fromDate)>=30){//30 days
                    doctorIsReady= true;
                }
            }
        }
        else{
            Date lastConsultDate= consults.get(consults.size()-1).getDate();
            if(calculatediff(lastConsultDate)>=30){//30 days
                doctorIsReady= true;
            }
        }
        return doctorIsReady;
    }

    public List<PatientData> getQueryResult(Patient patient){
        TypedQuery<PatientData> query= em.createQuery("FROM PatientData p WHERE pData_id=:param", PatientData.class);
        query.setParameter("param", patient.getId());
        List<PatientData> pdataList= query.getResultList();
        return pdataList;
    }

    public long calculatediff(Date fromDate){
        Date toDate =new Date();
        long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;

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

