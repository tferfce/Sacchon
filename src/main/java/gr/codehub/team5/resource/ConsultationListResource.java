package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;

import java.util.List;

/**
 * Here we get all the consults a specific patient has.
 * We define the patient judging from his id in the API
 */
public interface ConsultationListResource {

    @Get
    List<ConsultationRepresentation> getConsults() throws NotFoundException;
}
