package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Administrator;
import lombok.Data;

@Data
public class AdministratorRepresentation {

    private String firstName;
    private String lastName;

    private String uri;

    //Creates Administrator from AdministratorRepresentation
    public Administrator getAdministrator(AdministratorRepresentation administratorRepresentation){
        Administrator administrator = new Administrator();
        administrator.setFirstName(administratorRepresentation.getFirstName());
        administrator.setLastName(administratorRepresentation.getLastName());
        return administrator;
    }

    //Creates AdministratorRepresentation from Administrator
    public AdministratorRepresentation getAdministratorRepresentation(Administrator administrator){
        AdministratorRepresentation administratorRepresentation = new AdministratorRepresentation();
        administratorRepresentation.setFirstName(administrator.getFirstName());
        administratorRepresentation.setLastName(administrator.getLastName());
        return administratorRepresentation;
    }
}
