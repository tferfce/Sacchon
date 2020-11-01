package gr.codehub.team5.resource;


import gr.codehub.team5.representation.PatientRepresentation;

import org.restlet.resource.Post;



/**
 * The first request is the register of the Patient with full Access
 */
public interface PatientListResource {
    @Post("json")
    PatientRepresentation addPatient(PatientRepresentation patientRepresentation) throws Exception;
}
