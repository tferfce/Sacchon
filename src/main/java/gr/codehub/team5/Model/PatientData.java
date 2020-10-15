package gr.codehub.team5.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private Date date;

    public PatientData(double carbIntake, double bloodGlucose) {
        this.carbIntake = carbIntake;
        this.bloodGlucose = bloodGlucose;
        this.date = new Date();
    }

    @ManyToOne
    private Patient pData;
}
