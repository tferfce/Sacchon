package gr.codehub.team5.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@SQLDelete(sql = "UPDATE user SET deleted=true WHERE id=?")
//@Where(clause = "deleted = false")
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean deleted;




    public Doctor(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
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
