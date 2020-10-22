package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface DoctorResource {

    @Get("json")
    public DoctorRepresentation getDoctor() throws NotFoundException;

    @Put("json")
    public DoctorRepresentation update(DoctorRepresentation doctorReprIn)
            throws NotFoundException, BadEntityException;
}
