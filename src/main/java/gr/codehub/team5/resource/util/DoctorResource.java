package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public interface DoctorResource {

    @Delete ("json")
    public void remove()
            throws NotFoundException;

    @Get ("json")
    DoctorRepresentation getDoctor() throws NotFoundException, ResourceException;

}
