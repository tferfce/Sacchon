package gr.codehub.team5.Model;

import gr.codehub.team5.security.CustomRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * DB Model of a Patient
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
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
    private boolean Active = true;
    private CustomRole customRole = CustomRole.ROLE_PATIENT;

    @Column(nullable = false)
    private String gender;

    public Patient(String firstName, String lastName, String userName, String password, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Consultations> consultations;

    @OneToMany(mappedBy = "pData",fetch = FetchType.EAGER)
    private List<PatientData> patientData;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
