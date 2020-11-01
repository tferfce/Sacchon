package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.util.List;

/**
 * Request to Get all Patients for Chief Doctor use
 */
public interface AllPatientsList {

    @Get("json")
    List<PatientRepresentation> getPatients() throws NotFoundException;
}
