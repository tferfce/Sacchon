package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.representation.DoctorRepresentation;
import gr.codehub.team5.resource.util.DoctorResource;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class DoctorResourceImpl extends ServerResource implements DoctorResource {

    private DoctorRepository doctorRepository ;
    private EntityManager em;
    private long id;

    @Override
    public void remove() throws NotFoundException{

        doctorRepository.deleteById(id);

    }

    @Override
    public DoctorRepresentation getDoctor() throws NotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        setExisting(doctor.isPresent());
        if (!doctor.isPresent())  throw new NotFoundException("Customer is not found");
        DoctorRepresentation doctorRepresentation = DoctorRepresentation.getDoctorRepresentation(doctor.get());
        return doctorRepresentation;
    }
}
