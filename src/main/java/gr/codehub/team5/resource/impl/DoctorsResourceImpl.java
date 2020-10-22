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
    public DoctorRepresentation add(DoctorRepresentation doctorIn) throws BadEntityException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());

        if (doctorIn==null) throw new  BadEntityException("Null doctor representation error");

        Doctor doctor = DoctorRepresentation.getDoctor(doctorIn);

        doctorRepository.save(doctor);
        return DoctorRepresentation.getDoctorRepresentation(doctor);
    }

    @Override
    public List<DoctorRepresentation> getDoctors() throws NotFoundException {
        List<Doctor> doctors= doctorRepository.findAll();

        List<DoctorRepresentation> doctorRepresentationList = new ArrayList<>();

        doctors.forEach(doctor -> doctorRepresentationList.add(DoctorRepresentation.getDoctorRepresentation(doctor)));

        return doctorRepresentationList;
    }
}
