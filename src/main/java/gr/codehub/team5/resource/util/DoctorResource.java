package gr.codehub.team5.resource.util;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.DoctorRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

public interface DoctorResource {

    @Delete("json")
    public DoctorRepresentation remove() throws ResourceException;

    @Get ("json")
    public DoctorRepresentation getDoctor() throws NotFoundException, ResourceException;

    @Put("json")
    public DoctorRepresentation update(DoctorRepresentation doctorReprIn) throws NotFoundException, BadEntityException;

}
