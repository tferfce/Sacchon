package gr.codehub.team5.resource.util;

import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface PatientToDoctorResource {

    @Get
    public PatientRepresentation setDoctorToPatient() throws ResourceException;
}
