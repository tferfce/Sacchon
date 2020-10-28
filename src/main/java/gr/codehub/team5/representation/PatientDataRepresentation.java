package gr.codehub.team5.representation;

import gr.codehub.team5.Model.PatientData;
import lombok.Data;

import java.util.Date;

@Data

public class PatientDataRepresentation {

    private double carbIntake;
    private double bloodGlucose;
    private Date date= new Date();


    private String uri;

    static public PatientData getPatientData(PatientDataRepresentation patientDataRepresentation){

        PatientData patientData = new PatientData();

        patientData.setBloodGlucose(patientDataRepresentation.getBloodGlucose());
        patientData.setCarbIntake(patientDataRepresentation.getCarbIntake());
        //patientData.setDate(patientDataRepresentation.getDate());
        return patientData;

    }

    static public PatientDataRepresentation getPatientDataRepresentation(PatientData patientData){

        PatientDataRepresentation patientDataRepresentation= new PatientDataRepresentation();
        patientDataRepresentation.setBloodGlucose(patientData.getBloodGlucose());
        patientDataRepresentation.setCarbIntake(patientData.getCarbIntake());
        //patientDataRepresentation.setDate(patientData.getDate());

        patientDataRepresentation.setUri("http://localhost:9000/project/patientData/"+patientData.getId());

        return patientDataRepresentation;
    }
}
