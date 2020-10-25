package gr.codehub.team5.resource.impl;

import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.resource.LoginResource;
import gr.codehub.team5.security.CustomRole;
import gr.codehub.team5.security.dao.ApplicationUser;
import org.restlet.Request;
import org.restlet.resource.ServerResource;
import org.restlet.security.User;

import java.util.List;

public class LoginResourceImpl extends ServerResource implements LoginResource {
    @Override
    public ApplicationUser getUser() throws NotFoundException {
        // Role
        List role = Request.getCurrent().getClientInfo().getRoles();
        User user = Request.getCurrent().getClientInfo().getUser();
        char[] pass = getChallengeResponse().getSecret();
        ApplicationUser app_user = new ApplicationUser(user.getIdentifier(), String.valueOf(pass), CustomRole.getRoleValue(role.get(0).toString()));
        return app_user;
    }
}
