package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.repository.lib.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for Doctor
 */
public class DoctorRepository extends Repository<Doctor, Long> {

    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    public String getEntityClassName() {
        return Doctor.class.getName();
    }

    public List<Doctor> findAllWithNoActivity() {
        TypedQuery<Doctor> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }
}
