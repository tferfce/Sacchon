package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Here its a Request for admin only getting consults of each doctor judging by the id of the doctor.
 * We are using **params** to specify the dates needed to calculate the consults.
 */
public interface AdminConsultsForOfEachDoctor {

    @Get
    List<ConsultationRepresentation> getConsultsForEveryDoc() throws NotFoundException, IOException, ParseException;
}
