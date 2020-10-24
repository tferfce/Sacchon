package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.security.dao.ApplicationUser;
import org.restlet.resource.Get;

public interface LoginResource {

    @Get("json")
    public ApplicationUser getUser() throws NotFoundException;

}
