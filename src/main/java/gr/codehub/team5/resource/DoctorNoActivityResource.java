package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * This request is used by the admin and Get us all the doctors with 0 consults despite having an active account
 */
public interface DoctorNoActivityResource {

    @Get("json")
    List<DoctorRepresentation> getDoctorsWithNoActivity() throws NotFoundException, ParseException, IOException;
}
