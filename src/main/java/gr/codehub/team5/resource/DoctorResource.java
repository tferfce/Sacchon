package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

/**
 * The first Request give us the specific doctor mostly for personal use
 * The second is used by the Doctor and gives him the ability to edit his personal data with the **Exception** of username which is unique!
 */
public interface DoctorResource {

    @Get("json")
    DoctorRepresentation getDoctor() throws NotFoundException;

    @Put("json")
    DoctorRepresentation update(DoctorRepresentation doctorReprIn)
            throws NotFoundException, BadEntityException;
    @Delete("json")
    void remove() throws ResourceException, NotFoundException;
}
