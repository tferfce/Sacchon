package gr.codehub.team5.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Consultations")
public class Consultations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String consult;
    private Date date = new Date();

    public Consultations(String consult,Doctor doctor, Patient patient) {
        this.patId = patient;
        this.docId = doctor;
        this.consult = consult;
    }


    @ManyToOne
    private Doctor docId;

    @ManyToOne
    private Patient patId;

    @Override
    public String toString() {
        return "Consultations{" +
                "id=" + id +
                ", consult='" + consult + '\'' +
                '}';
    }
}
