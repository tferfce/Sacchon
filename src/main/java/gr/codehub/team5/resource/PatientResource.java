package gr.codehub.team5.resource;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.representation.PatientRepresentation;
import org.restlet.resource.*;

public interface PatientResource {
    @Get("json")
    PatientRepresentation getPatient() throws NotFoundException, ResourceException;
    @Delete
    void remove() throws NotFoundException;
    @Put("json")
    PatientRepresentation updatePatient(PatientRepresentation patientRepresentation)
        throws NotFoundException, BadEntityException;
    @Post("json")
    PatientDataRepresentation addPatientData(PatientData patientData) throws BadEntityException,NotFoundException;

}
