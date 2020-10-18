package gr.codehub.team5.resource;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface PatientListResource {
    @Post("json")
    Patient addPatient(Patient patient) throws BadEntityException;
    @Get("json")
    List<Patient> getAllPatients() throws NotFoundException;

}
