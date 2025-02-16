package gr.codehub.team5.Model;

import gr.codehub.team5.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * DB Model of a Doctor
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private boolean Active = true;
    private CustomRole customRole = CustomRole.ROLE_DOCTOR;

    public Doctor(String firstName, String lastName, String userName,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Patient> listOfPatients;

    @OneToMany(mappedBy = "docId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Consultations> consultations;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
