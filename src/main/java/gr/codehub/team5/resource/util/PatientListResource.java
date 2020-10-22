package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface PatientListResource {

    @Post("json")
    public PatientRepresentation addPatient(PatientRepresentation patientIn)
            throws BadEntityException;
    @Get("json")
    public List<PatientRepresentation> getAllPatients() throws NotFoundException;


}