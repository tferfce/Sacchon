package gr.codehub.team5.Model;

import gr.codehub.team5.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
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
