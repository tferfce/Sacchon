package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.AdministratorRepresentation;
import org.restlet.resource.Get;


/**
 * For personal use also to get the admin we added!
 */
public interface AdminResourceGet {
    @Get
    AdministratorRepresentation getAdmin() throws NotFoundException;
}
