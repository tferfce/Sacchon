package gr.codehub.team5.resource.loginUser;

import gr.codehub.team5.security.CustomRole;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A user representation used for login form to add the id on our JSON object when Auth login
 */
@Data
@NoArgsConstructor
public class LoginUserPersistence {
    private long id;
    private String username;
    private String password;
    private CustomRole role;

}
