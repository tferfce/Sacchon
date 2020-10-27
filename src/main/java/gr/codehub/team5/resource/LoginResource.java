package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.resource.loginUser.LoginUserPersistence;
import org.restlet.resource.Get;

public interface LoginResource {

    @Get("json")
    public LoginUserPersistence getUser() throws NotFoundException;

}
