package gr.codehub.team5.Model;
import gr.codehub.team5.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private CustomRole customRole = CustomRole.ROLE_CHIEFDOCTOR;

    public Administrator(String firstName, String lastName, String userName,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
}
