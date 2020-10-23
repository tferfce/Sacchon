package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.AdministratorRepository;
import gr.codehub.team5.representation.AdministratorRepresentation;
import gr.codehub.team5.resource.AdminResourceGet;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class AdminResourceGetImpl extends ServerResource implements AdminResourceGet {
    private EntityManager em;
    private long id;
    private AdministratorRepository administratorRepository;

    public AdminResourceGetImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
        id = Long.parseLong(getAttribute("id"));
        administratorRepository = new AdministratorRepository(em);
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public AdministratorRepresentation getAdmin() throws NotFoundException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        Optional<Administrator> administratorOptional = administratorRepository.findById(id);
        if (!administratorOptional.isPresent()) throw new NotFoundException("Patient is not found");
        AdministratorRepresentation administratorRepresentation = AdministratorRepresentation.getAdministratorRepresentation(administratorOptional.get());
        return administratorRepresentation;
    }
}
