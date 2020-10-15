package gr.codehub.team5.repository;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.repository.lib.Repository;
import javax.persistence.EntityManager;

public class AdministratorRepo extends Repository<Administrator, Long> {
    public AdministratorRepo(EntityManager entityManager) {
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
