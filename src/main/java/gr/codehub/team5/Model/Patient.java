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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private CustomRole customRole = CustomRole.ROLE_PATIENT;

    public Patient(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Consultations> consultations;

    @OneToMany(mappedBy = "pData",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PatientData> patientData;

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
