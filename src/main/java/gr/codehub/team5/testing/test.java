package gr.codehub.team5.testing;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;

import javax.persistence.EntityManager;

public class test {

    public static void main(String[] args) {
        EntityManager em = SacchonJpa.getEntityManager();
        DoctorRepository drRepo = new DoctorRepository(em);
        PatientRepository patRepo = new PatientRepository(em);
        ConsultationRepository consRepo = new ConsultationRepository(em);
        PatientDataRepository pdataRepo = new PatientDataRepository(em);

        Doctor doc = new Doctor("Kyros","Granazhs","personalUSR", "somethingelse");
        drRepo.save(doc);

        Patient pat1 = new Patient("Thomas","Ferfelis","something", "something");
        patRepo.save(pat1);
        pat1.setDoctor(doc);
        patRepo.save(pat1);

        Consultations consultations = new Consultations("This is a not very big sentence",doc,pat1);
        consRepo.save(consultations);


        PatientData pdata = new PatientData(325.123,245.32);
        pdata.setPData(pat1);
        pdataRepo.save(pdata);
    }
}

