package gr.codehub.team5.repository;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;

public class PatientDataRepository extends Repository<PatientData, Long> {
    public PatientDataRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<PatientData> getEntityClass() {
        return PatientData.class;
    }

    @Override
    public String getEntityClassName() {
        return PatientData.class.getName();
    }
}
