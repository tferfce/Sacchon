package gr.codehub.team5.resource.impl;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.resource.util.DoctorResource;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class DoctorResourceImpl extends ServerResource implements DoctorResource {

    private DoctorRepository doctorRepository ;
    private EntityManager em;
    private long id;

    @Override
    public void remove() throws NotFoundException{

        doctorRepository.deleteById(id);










    }
}
