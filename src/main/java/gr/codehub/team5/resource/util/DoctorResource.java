package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Delete;

public interface DoctorResource {

    @Delete ("json")
    public void remove()
            throws NotFoundException;
}
