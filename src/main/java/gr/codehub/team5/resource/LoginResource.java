package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.resource.loginUser.LoginUserPersistence;
import org.restlet.resource.Get;

/**
 * The most crucial Request! Our login depends on it.
 * First we use the Auth to access the users and identify their information and more specific their role!
 * We do some more stuff in there checking all the DB for users and combine their info with the their id we find inside this method
 * so we return it in front end!
 * We also check active and inactive account and reject all inactive users(Patients or Doctors)
 */
public interface LoginResource {

    @Get("json")
    LoginUserPersistence getUser() throws NotFoundException;

}
