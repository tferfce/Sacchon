package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.PatientDataRepresentation;
import org.restlet.resource.Get;

import java.util.List;

public interface DoctorsPatientsResource {

    @Get("json")
    public List<PatientDataRepresentation> getDoctorsPatientData() throws NotFoundException;
}
