package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import java.util.List;

/**
 * Its a request used by the doctors and it shows the patient of a specific doctor and **also** the patients with no doctor that meets the condition
 * of 30 days worth of Data!
 */
public interface DoctorsPatientsWaitForConsultResource {
    @Get
    List<PatientRepresentation> getDoctorsPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException;
}
