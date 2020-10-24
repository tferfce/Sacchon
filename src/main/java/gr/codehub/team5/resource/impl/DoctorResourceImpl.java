package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.DoctorRepresentation;
import gr.codehub.team5.resource.util.DoctorResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DoctorResourceImpl extends ServerResource implements DoctorResource {

    private DoctorRepository doctorRepository ;
    private EntityManager em;
    private long id;
    //private long patientId;
    private PatientRepository patientRepository;
   

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            doctorRepository= new DoctorRepository(em);
            patientRepository = new PatientRepository(em);
            id=Long.parseLong(getAttribute("id"));
            //patientId=Long.parseLong(getAttribute("patientId"));
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    public DoctorRepresentation remove() throws ResourceException{

        Optional<Doctor> doctor = doctorRepository.findById(id);
        doctor.get().setDeleted(true);
        System.out.println(doctor.get().isDeleted());
        doctorRepository.save(doctor.get());

        TypedQuery <Patient> query= em.createQuery("FROM Patient P WHERE doctor_id=:param", Patient.class);
        query.setParameter("param",this.id);
        List<Patient> DoctorsPatients=query.getResultList();


        for (Patient patient: DoctorsPatients){
            patient.setDoctor(null);
            patientRepository.save(patient);
        }
        return DoctorRepresentation.getDoctorRepresentation(doctor.get());

    }

    @Override
    public DoctorRepresentation getDoctor() throws NotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        setExisting(doctor.isPresent());
        if (!doctor.isPresent())  throw new NotFoundException("Customer is not found");
        DoctorRepresentation doctorRepresentation = DoctorRepresentation.getDoctorRepresentation(doctor.get());
        return doctorRepresentation;
    }

    @Override
    public DoctorRepresentation update(DoctorRepresentation doctorReprIn) throws NotFoundException, BadEntityException {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given doctor id is not existing");
        Doctor doctor = doctorOpt.get();

        doctor.setFirstName(doctorReprIn.getFirstName());
        doctor.setLastName(doctorReprIn.getLastName());
        doctor.setUserName(doctorReprIn.getUserName());
        doctor.setPassword(doctorReprIn.getPassword());

        doctorRepository.save(doctor);
        return DoctorRepresentation.getDoctorRepresentation(doctor);
    }
}
