package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.HashPatientsWaitingConsult;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.List;

/**
 * Chief Doctor use this Request.
 * There we get all patients that are ready for consult and the time they are waiting for this.
 * We decide to make a patient ready for consult when we have 30 days worth of Patient Data
 * So for example if he is waiting 39 days the time elapse is 9 days
 */
public interface AllPatientsWaitForConsultResource {
    @Get
    List<HashPatientsWaitingConsult> getAllPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException;
}
