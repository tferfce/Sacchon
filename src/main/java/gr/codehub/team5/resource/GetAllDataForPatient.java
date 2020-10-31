package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Get;

import java.util.List;

/**
 * This request is used by the Doctors giving all the data of a specific patient
 */
public interface GetAllDataForPatient {

    @Get
    List<PatientDataRepresentation> getAllData() throws NotFoundException;
}
