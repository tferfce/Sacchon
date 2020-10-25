package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface PatientDataResource {

    @Get("json")
    public List<PatientDataRepresentation> getPatientData() throws NotFoundException;

    @Post("json")
    public PatientDataRepresentation addPatientData(PatientDataRepresentation patientDataRepresentation)
            throws BadEntityException;
//
//    @Put("json")
//    public PatientDataRepresentation update(PatientDataRepresentation patientDataRepresentation)
//            throws NotFoundException , BadEntityException;
//
//    @Delete("json")
//    public PatientDataRepresentation remove(PatientDataRepresentation patientDataRepresentation)
//            throws NotFoundException, BadEntityException;



}
