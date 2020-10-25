package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface ConsulationsResource {
    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException, NotFoundException;

    @Get("json")
    public List<ConsultationRepresentation> getConsultations() throws NotFoundException;
}
