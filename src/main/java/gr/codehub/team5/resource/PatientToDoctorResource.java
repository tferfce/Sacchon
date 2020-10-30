package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

/**
 * This request is used by the Doctor giving him the ability to take a Patient that has no Doctor
 */
public interface PatientToDoctorResource {

    @Get
    PatientRepresentation setDoctorToPatient() throws ResourceException, NotFoundException;
}