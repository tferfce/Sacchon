package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ConsultationResource {

    @Get("json")
    public ConsultationRepresentation getConsultation() throws NotFoundException;

    @Put("json")
    public ConsultationRepresentation update(ConsultationRepresentation consultationReprIn)
            throws NotFoundException, BadEntityException;
}
