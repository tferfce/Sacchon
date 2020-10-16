package gr.codehub.team5.service;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientRepository;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class DoctorController extends ServerResource {
    EntityManager em = SacchonJpa.getEntityManager();
    DoctorRepository drRepo = new DoctorRepository(em);
    PatientRepository patRepo = new PatientRepository(em);

    @Post
    public Doctor addDoctor(Doctor doctor){
        drRepo.save(doctor);
        return doctor;
    }
    @Get("json")
    public List<Doctor> getAll(){
        return drRepo.findAll();
    }
    
    @Put
    public Doctor addPatient(){
        Patient patient = patRepo.findById(1l).get();
        Doctor doctor = drRepo.findById(1l).get();
        doctor.getListOfPatients().add(patient);
        drRepo.save(doctor);
        return doctor;
    }

}
