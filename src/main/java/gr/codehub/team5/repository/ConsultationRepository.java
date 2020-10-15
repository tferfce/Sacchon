package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;

public class ConsultationRepository extends Repository<Consultations, Long> {

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Consultations> getEntityClass() {
        return Consultations.class;
    }

    @Override
    public String getEntityClassName() {
        return Consultations.class.getName();
    }
}
