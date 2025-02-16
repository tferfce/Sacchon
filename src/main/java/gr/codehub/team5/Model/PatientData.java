package gr.codehub.team5.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * DB Model of Patient Data
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PatientData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double carbIntake;
    private double bloodGlucose;
    private Date date = new Date();

    public PatientData(double carbIntake, double bloodGlucose) {
        this.carbIntake = carbIntake;
        this.bloodGlucose = bloodGlucose;
    }

    @ManyToOne
    private Patient pData;

    @Override
    public String toString() {
        return "PatientData{" +
                "id=" + id +
                ", carbIntake=" + carbIntake +
                ", bloodGlucose=" + bloodGlucose +
                ", date=" + date +
                '}';
    }
}
