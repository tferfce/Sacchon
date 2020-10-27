package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AdminConsultsForOfEachDoctor {

    @Get
    List<ConsultationRepresentation> getConsultsForEveryDoc(String dates) throws NotFoundException, IOException, ParseException;
}
