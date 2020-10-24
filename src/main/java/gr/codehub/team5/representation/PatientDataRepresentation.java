package gr.codehub.team5.representation;

import gr.codehub.team5.Model.PatientData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientDataRepresentation {
    private long id;
    private double carbIntake;
    private double bloodGlucose;

    private int date = LocalDateTime.now().getDayOfYear();

    public static PatientDataRepresentation getDataRepresentation(PatientData patientData){
        PatientDataRepresentation patientDataRepresentation = new PatientDataRepresentation();
        patientDataRepresentation.setId(patientData.getId());
        patientDataRepresentation.setCarbIntake(patientData.getCarbIntake());
        patientDataRepresentation.setBloodGlucose(patientData.getBloodGlucose());
        return patientDataRepresentation;
    }
}
