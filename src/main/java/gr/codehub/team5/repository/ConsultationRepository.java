package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

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

    public List<Consultations> findByTimeRange (Date fromDate, Date toDate){

        List<Consultations> consultations = entityManager.createQuery("from Consultations c where c.date >= :fromDate and c.date <= :toDate")
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();

        return consultations;

    }
}
