package gr.codehub.team5.resource;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Post;

public interface AdministratorResource {

    @Post
    Administrator addAdmin(Administrator admin) throws Exception;

}
