package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DoctorNoActivityResource {

    @Get("json")
    List<DoctorRepresentation> getDoctorsWithNoActivity(String dates) throws NotFoundException, ParseException, IOException;
}
