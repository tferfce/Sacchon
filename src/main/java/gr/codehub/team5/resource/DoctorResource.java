package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Post;

public interface DoctorResource {
    @Post("json")
    public DoctorRepresentation add(DoctorRepresentation doctorIn) throws BadEntityException;
}
