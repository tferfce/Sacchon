package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.HashMap;

public interface AllPatientsWaitForConsultResource {

    @Get
    HashMap<PatientRepresentation, Long> getAllPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException;
}
