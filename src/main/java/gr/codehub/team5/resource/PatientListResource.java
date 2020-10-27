package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Post;

public interface PatientListResource {
    @Post("json")
    PatientRepresentation addPatient(PatientRepresentation patientRepresentation) throws BadEntityException;
}
