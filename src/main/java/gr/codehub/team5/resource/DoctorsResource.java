package gr.codehub.team5.resource;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;


/**
 * The first request is used by the Admin and helps him add a doctor in DB after providing his Credentials
 * The second is used also by the admin and shows all doctors in the Database, active or inactive
 */
public interface DoctorsResource {

    @Post("json")
    DoctorRepresentation add(DoctorRepresentation doctorIn) throws Exception;

    @Get("json")
    List<DoctorRepresentation> getDoctors() throws NotFoundException;
}
