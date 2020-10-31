package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * This request is used By the Chief Doctor showing him all the Patients that don't have any stored data
 */
public interface PatientsNoActivityResource {

    @Get
    List<PatientRepresentation> getPatientsWithNoActivity() throws NotFoundException, ParseException, IOException;
}
