package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.representation.DoctorRepresentation;
import gr.codehub.team5.resource.DoctorsResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DoctorsResourceImpl extends ServerResource implements DoctorsResource {

    private DoctorRepository doctorRepository;
    private EntityManager em;
    private long id;



    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            doctorRepository = new DoctorRepository(em);
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
    public DoctorRepresentation add(DoctorRepresentation doctorIn) throws Exception {
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        if (doctorIn==null) throw new  BadEntityException("Null doctor error");
        if (doctorIn.getFirstName()==null
                || doctorIn.getLastName()==null
                || doctorIn.getPassword()==null
                || doctorIn.getUserName()==null){
            throw new NotFoundException("Invalid Credentials");
        }
        userNameCheck(doctorIn);

        Doctor doctor = DoctorRepresentation.getDoctor(doctorIn);
        doctorRepository.save(doctor);
        return DoctorRepresentation.getDoctorRepresentation(doctor);
    }

    private void userNameCheck(DoctorRepresentation doctorIn) throws Exception {
        TypedQuery<Long> query = em.createQuery("SELECT p.id FROM Doctor p WHERE p.userName=:param",Long.class);
        query.setParameter("param", doctorIn.getUserName());
        List<Long> doclist = query.getResultList();
        if (!doclist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query1 = em.createQuery("SELECT p.id FROM Administrator p WHERE p.userName=:param",Long.class);
        query1.setParameter("param", doctorIn.getUserName());
        List<Long> adminlist = query1.getResultList();
        if (!adminlist.isEmpty()) throw new Exception("Username already in use!");

        TypedQuery<Long> query2 = em.createQuery("SELECT p.id FROM Patient p WHERE p.userName=:param",Long.class);
        query2.setParameter("param", doctorIn.getUserName());
        List<Long> patientlist = query2.getResultList();
        if (!patientlist.isEmpty()) throw new Exception("Username already in use!");
    }

    @Override
    public List<DoctorRepresentation> getDoctors(){
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        List<Doctor> doctors= doctorRepository.findAll();

        List<DoctorRepresentation> doctorRepresentationList = new ArrayList<>();

        doctors.forEach(doctor -> doctorRepresentationList.add(DoctorRepresentation.getDoctorRepresentation(doctor)));

        return doctorRepresentationList;
    }
}
