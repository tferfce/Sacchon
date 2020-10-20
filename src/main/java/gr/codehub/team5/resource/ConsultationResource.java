package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Post;

public interface ConsultationResource {
        @Post("json")
        public ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException;
}
