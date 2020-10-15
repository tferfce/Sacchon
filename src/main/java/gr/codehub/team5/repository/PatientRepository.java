package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;

public class PatientRepository extends Repository<Patient, Long> {

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Patient> getEntityClass() {
        return Patient.class;
    }

    @Override
    public String getEntityClassName() {
        return Patient.class.getName();
    }
}
