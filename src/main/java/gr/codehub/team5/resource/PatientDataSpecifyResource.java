package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

public interface PatientDataSpecifyResource {
    @Delete
    void deleteSpecificData() throws NotFoundException;
    @Put
    PatientDataRepresentation updatePData(PatientDataRepresentation patientDataRepresentation) throws NotFoundException, BadEntityException;
}
