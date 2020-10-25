package gr.codehub.team5.repository;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

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

    public List<PatientData> findOverTimeRange(Date dateFrom, Date dateTo) {

        List<PatientData> pd = entityManager.createQuery("from PatientData p WHERE p.date >= :fromDate and p.date <= :toDate")
                .setParameter("fromDate", dateFrom)
                .setParameter("toDate", dateTo)
                .getResultList();
        return pd;
    }
}
