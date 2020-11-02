package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Administrator;
import gr.codehub.team5.security.CustomRole;
import lombok.Data;

/**
 * Representation of the Chief Doctor for Front end use
 */
@Data
public class AdministratorRepresentation {
    private long id;
    private String firstName;
    private String lastName;
    private CustomRole customRole;


    public static Administrator getAdministrator(AdministratorRepresentation administratorRepresentation){
        Administrator administrator = new Administrator();
        administrator.setFirstName(administratorRepresentation.getFirstName());
        administrator.setLastName(administratorRepresentation.getLastName());
        return administrator;
    }


    public static AdministratorRepresentation getAdministratorRepresentation(Administrator administrator){
        AdministratorRepresentation administratorRepresentation = new AdministratorRepresentation();
        administratorRepresentation.setId(administrator.getId());
        administratorRepresentation.setCustomRole(administrator.getCustomRole());
        administratorRepresentation.setFirstName(administrator.getFirstName());
        administratorRepresentation.setLastName(administrator.getLastName());
        return administratorRepresentation;
    }
}
