package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * A request used by every patient giving him access to his data over a specific period
 */
public interface PatientDataResource {
    @Get("json")
    List<PatientDataRepresentation> getPatientData() throws NotFoundException, IOException, ParseException;
}
