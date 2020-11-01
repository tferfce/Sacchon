package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.PatientListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientListResourceImpl extends ServerResource implements PatientListResource  {
    private EntityManager em;
    PatientRepository patientRepository;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public PatientRepresentation addPatient(PatientRepresentation patientRepresentation) throws Exception {
        if (patientRepresentation == null) throw new BadEntityException("Null patient error");
        userNameCheck(patientRepresentation);
        Patient patient = PatientRepresentation.getPatient(patientRepresentation);
        patientRepository.save(patient);
        return PatientRepresentation.getPatientRepresentation(patient);
    }

    private void userNameCheck(PatientRepresentation patientRepresentation) throws Exception {
        TypedQuery<Long> query = em.createQuery("SELECT p.id FROM Doctor p WHERE p.userName=:param",Long.class);
        query.setParameter("param", patientRepresentation.getUserName());
        List<Long> doclist = query.getResultList();
        if (!doclist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query1 = em.createQuery("SELECT p.id FROM Administrator p WHERE p.userName=:param",Long.class);
        query1.setParameter("param", patientRepresentation.getUserName());
        List<Long> adminlist = query1.getResultList();
        if (!adminlist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query2 = em.createQuery("SELECT p.id FROM Patient p WHERE p.userName=:param",Long.class);
        query2.setParameter("param", patientRepresentation.getUserName());
        List<Long> patientlist = query2.getResultList();
        if (!patientlist.isEmpty()) throw new Exception("Username already in use!");
    }

}
