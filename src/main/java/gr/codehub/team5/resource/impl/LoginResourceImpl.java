package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.resource.LoginResource;
import gr.codehub.team5.resource.loginUser.LoginUserPersistence;
import gr.codehub.team5.security.CustomRole;
import gr.codehub.team5.security.dao.ApplicationUser;
import org.restlet.Request;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.security.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoginResourceImpl extends ServerResource implements LoginResource {
    private EntityManager em;
    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }
    @Override
    public LoginUserPersistence getUser() throws NotFoundException {
        // Role
        List role = Request.getCurrent().getClientInfo().getRoles();
        User user = Request.getCurrent().getClientInfo().getUser();
        char[] pass = getChallengeResponse().getSecret();
        LoginUserPersistence loginUserPersistence = new LoginUserPersistence();
        ApplicationUser app_user = new ApplicationUser(user.getIdentifier(), String.valueOf(pass), CustomRole.getRoleValue(role.get(0).toString()));
        loginUserPersistence.setPassword(app_user.getPassword());
        loginUserPersistence.setRole(app_user.getRole());
        loginUserPersistence.setUsername(app_user.getUsername());
        TypedQuery<Long> query = em.createQuery("SELECT p.id FROM Doctor p WHERE p.userName=:param",Long.class);
        TypedQuery<Doctor> queryDoc = em.createQuery("FROM Doctor p WHERE p.userName=:param",Doctor.class);
        query.setParameter("param",user.getIdentifier());
        queryDoc.setParameter("param",user.getIdentifier());
        List<Doctor> docObj = queryDoc.getResultList();
        if (!docObj.isEmpty()){
            if (!docObj.get(0).isActive()) throw new NotFoundException("inactive Doctor");
        }
        List<Long> doclist = query.getResultList();
        if (!doclist.isEmpty()) loginUserPersistence.setId(doclist.get(0));

        TypedQuery<Long> query1 = em.createQuery("SELECT p.id FROM Administrator p WHERE p.userName=:param",Long.class);
        query1.setParameter("param",user.getIdentifier());
        List<Long> adminlist = query1.getResultList();
        if (!adminlist.isEmpty()) loginUserPersistence.setId(adminlist.get(0));

        TypedQuery<Long> query2 = em.createQuery("SELECT p.id FROM Patient p WHERE p.userName=:param",Long.class);
        query2.setParameter("param",user.getIdentifier());
        List<Long> patientlist = query2.getResultList();
        if (!patientlist.isEmpty()) loginUserPersistence.setId(patientlist.get(0));

        return loginUserPersistence;
    }
}
