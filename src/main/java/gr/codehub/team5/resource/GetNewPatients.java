package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.util.List;

public interface GetNewPatients {
    @Get("json")
    List<PatientRepresentation> getNewPatients() throws NotFoundException;
}
