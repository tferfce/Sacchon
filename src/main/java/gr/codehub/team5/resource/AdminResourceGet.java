package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.AdministratorRepresentation;
import org.restlet.resource.Get;

public interface AdminResourceGet {
    @Get
    AdministratorRepresentation getAdmin() throws NotFoundException;
}
