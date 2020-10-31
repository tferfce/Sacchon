package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.util.List;

/**
 * A request used by the Doctors having access to all New patients and their stored data!
 */
public interface GetNewPatients {
    @Get("json")
    List<PatientRepresentation> getNewPatients() throws NotFoundException;
}
