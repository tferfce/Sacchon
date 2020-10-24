package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Get;

import java.util.List;

public interface AdminDataForEachPatient {
    @Get("json")
    List<PatientDataRepresentation> getPatientData() throws NotFoundException;
}
