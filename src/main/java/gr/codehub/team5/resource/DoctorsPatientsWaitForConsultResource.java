package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.List;

public interface DoctorsPatientsWaitForConsultResource {
    @Get
    List<PatientRepresentation> getDoctorsPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException;
}
