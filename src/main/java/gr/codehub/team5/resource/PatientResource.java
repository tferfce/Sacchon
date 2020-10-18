package gr.codehub.team5.resource;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface PatientResource {
    @Get
    Patient getPatient() throws NotFoundException, ResourceException;
}
