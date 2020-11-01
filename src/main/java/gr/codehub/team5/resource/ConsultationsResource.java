package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

/**
 * The first method is used by the Doctor to post consult in specific Patient
 * There we give the consult, the id of the doctor and the id of the Patient
 * When the doctor decide to Consult a Patient with no doctor he auto grabs him
 * The second method is for personal use and give us all Consults in our DB
 */
public interface ConsultationsResource {
        @Post("json")
        ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException, NotFoundException;

        @Get("json")
        List<ConsultationRepresentation> getConsultations() throws NotFoundException;
}
