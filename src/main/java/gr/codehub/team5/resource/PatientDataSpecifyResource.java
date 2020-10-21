package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Delete;

public interface PatientDataSpecifyResource {
    @Delete
    void deleteSpecificData() throws NotFoundException;
}
