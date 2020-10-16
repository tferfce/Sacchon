package gr.codehub.team5.service;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientController extends ServerResource {
    EntityManager em = SacchonJpa.getEntityManager();
    PatientRepository patRepo = new PatientRepository(em);

    @Post
    public Patient addPatient(Patient patient){
        patRepo.save(patient);
        return patient;
    }
    @Get
    public List<Patient> getAll(){
        return patRepo.findAll();
    }
}
