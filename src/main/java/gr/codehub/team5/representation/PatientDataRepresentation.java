package gr.codehub.team5.representation;

import gr.codehub.team5.Model.PatientData;
import lombok.Data;

import java.util.Date;
@Data
public class PatientDataRepresentation {
    private double carbIntake;
    private double bloodGlucose;
    private Date date = new Date();

    public static PatientDataRepresentation getDataRepresentation(PatientData patientData){
        PatientDataRepresentation patientDataRepresentation = new PatientDataRepresentation();
        patientDataRepresentation.setCarbIntake(patientData.getCarbIntake());
        patientDataRepresentation.setBloodGlucose(patientData.getBloodGlucose());
        return patientDataRepresentation;
    }
}
