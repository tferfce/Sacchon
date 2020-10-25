package gr.codehub.team5.resource;

import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface PatientToDoctorResource {

    @Get
    PatientRepresentation setDoctorToPatient() throws ResourceException;
}