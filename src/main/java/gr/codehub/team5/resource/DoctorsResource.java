package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface DoctorsResource {

    @Post("json")
    DoctorRepresentation add(DoctorRepresentation doctorIn) throws BadEntityException;

    @Get("json")
    List<DoctorRepresentation> getDoctors() throws NotFoundException;
}
