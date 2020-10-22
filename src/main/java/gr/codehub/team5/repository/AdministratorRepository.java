package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.repository.lib.Repository;
import javax.persistence.EntityManager;

public class AdministratorRepository extends Repository<Administrator, Long> {
    public AdministratorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Administrator> getEntityClass() {
        return Administrator.class;
    }

    @Override
    public String getEntityClassName() {
        return Administrator.class.getName();
    }
}
