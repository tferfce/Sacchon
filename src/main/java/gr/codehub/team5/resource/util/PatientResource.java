package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface PatientResource {
    @Get
    PatientRepresentation getPatient() throws NotFoundException, ResourceException;
}
