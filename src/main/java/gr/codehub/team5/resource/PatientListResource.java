package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface PatientListResource {
    @Post("json")
    PatientRepresentation addPatient(PatientRepresentation patientRepresentation) throws Exception;
    @Get
    List<PatientRepresentation> getAllPatients() throws NotFoundException;
}
