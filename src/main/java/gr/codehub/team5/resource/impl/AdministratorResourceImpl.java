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
import javax.persistence.TypedQuery;
import java.util.List;

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
    public Administrator addAdmin(Administrator admin) throws Exception {
        if (admin==null) throw new BadEntityException("Null admin error");
        if (admin.getFirstName()==null
                || admin.getLastName()==null
                || admin.getPassword()==null
                || admin.getUserName()==null){
            throw new NotFoundException("Invalid Credentials");
        }
        userNameCheck(admin);
        administratorRepository.save(admin);
        return admin;
    }

    private void userNameCheck(Administrator admin) throws Exception {
        TypedQuery<Long> query = em.createQuery("SELECT p.id FROM Doctor p WHERE p.userName=:param",Long.class);
        query.setParameter("param", admin.getUserName());
        List<Long> doclist = query.getResultList();
        if (!doclist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query1 = em.createQuery("SELECT p.id FROM Administrator p WHERE p.userName=:param",Long.class);
        query1.setParameter("param", admin.getUserName());
        List<Long> adminlist = query1.getResultList();
        if (!adminlist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query2 = em.createQuery("SELECT p.id FROM Patient p WHERE p.userName=:param",Long.class);
        query2.setParameter("param", admin.getUserName());
        List<Long> patientlist = query2.getResultList();
        if (!patientlist.isEmpty()) throw new Exception("Username already in use!");
    }
}
