package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

/**
 * The first method is for personal use only giving us specific consults judging by the id
 * The second method is used by the Doctor to edit the specific consult he made avoiding mistakes
 */
public interface ConsultationResource {

    @Get("json")
    ConsultationRepresentation getConsultation() throws NotFoundException;

    @Put("json")
    ConsultationRepresentation update(ConsultationRepresentation consultationReprIn)
            throws NotFoundException, BadEntityException;
}
