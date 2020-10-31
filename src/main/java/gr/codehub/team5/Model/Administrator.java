package gr.codehub.team5.Model;
import gr.codehub.team5.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * DB Model of Chief Doctor
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private CustomRole customRole = CustomRole.ROLE_CHIEFDOCTOR;

    public Administrator(String firstName, String lastName, String userName,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
}
