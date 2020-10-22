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
public class Consultations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String consult;
    private Date date = new Date();

    public Consultations(String consult) {
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
                ", date=" + date +
                '}';
    }
}
