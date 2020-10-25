package gr.codehub.team5.representation;

import gr.codehub.team5.Model.PatientData;
import lombok.Data;

import java.util.Date;

@Data
public class PatientDataRepresentation {
    private long id;
    private double carbIntake;
    private double bloodGlucose;
    private Date date = new Date();

    public static PatientDataRepresentation getDataRepresentation(PatientData patientData){
        PatientDataRepresentation patientDataRepresentation = new PatientDataRepresentation();
        patientDataRepresentation.setId(patientData.getId());
        patientDataRepresentation.setCarbIntake(patientData.getCarbIntake());
        patientDataRepresentation.setBloodGlucose(patientData.getBloodGlucose());
        return patientDataRepresentation;
    }

    static public PatientData getPatientData(PatientDataRepresentation patientDataRepresentation){

        PatientData patientData = new PatientData();

        patientData.setBloodGlucose(patientDataRepresentation.getBloodGlucose());
        patientData.setCarbIntake(patientDataRepresentation.getCarbIntake());
        return patientData;

    }
}
