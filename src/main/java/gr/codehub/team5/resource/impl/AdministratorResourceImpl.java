package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.AdministratorRepository;
import gr.codehub.team5.resource.AdministratorResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class AdministratorResourceImpl extends ServerResource implements AdministratorResource {

    private AdministratorRepository administratorRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            administratorRepository = new AdministratorRepository(em);
        }
        catch(Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public Administrator addAdmin(Administrator admin) throws NotFoundException, BadEntityException {
        if (admin==null) throw new BadEntityException("Null admin error");
        //ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        administratorRepository.save(admin);
        return admin;
    }
}
