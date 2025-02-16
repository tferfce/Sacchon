package gr.codehub.team5.security.dao;


import gr.codehub.team5.security.CustomRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * App user Model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser{
    private String username;
    private String password;
    private CustomRole role;
}