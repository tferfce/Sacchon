package gr.codehub.team5.repository;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Repository for Patient
 * findByTimeRange -> Searching with time borders
 */
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


    public List<PatientData> findByTimeRange (Date fromDate, Date toDate){
        List<PatientData> patientData = entityManager.createQuery("from PatientData c where c.date >= :fromDate and c.date <= :toDate")
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();

        return patientData;

    }

}
