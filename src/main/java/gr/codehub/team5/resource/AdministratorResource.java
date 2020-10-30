package gr.codehub.team5.resource;
import gr.codehub.team5.Model.Administrator;
import org.restlet.resource.Post;

/**
 * Its for personal use only to add an Administrator to monitor all our programme!
 */
public interface AdministratorResource {

    @Post
    Administrator addAdmin(Administrator admin) throws Exception;

}
