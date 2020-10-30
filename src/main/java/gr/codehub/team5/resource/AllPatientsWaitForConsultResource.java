package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.HashPatientsWaitingConsult;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.HashMap;
import java.util.List;

public interface AllPatientsWaitForConsultResource {
    @Get
    List<HashPatientsWaitingConsult> getAllPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException;
}
