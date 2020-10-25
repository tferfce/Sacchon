package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;

import java.text.ParseException;
import java.util.List;

public interface PatientsNoActivityResource {

    @Get
    public List<PatientRepresentation> getPatientsWithNoActivity(String dates) throws NotFoundException, ParseException;
}
