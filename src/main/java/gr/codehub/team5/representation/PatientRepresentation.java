package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Patient;
import lombok.Data;

@Data
public class PatientRepresentation {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    private String uri;

    static public Patient getPatient(PatientRepresentation patientRepresentation){
        Patient patient = new Patient();

        patient.setFirstName(patientRepresentation.getFirstName());
        patient.setLastName(patientRepresentation.getLastName());
        patient.setUserName(patientRepresentation.getUserName());
        patient.setPassword(patientRepresentation.getPassword());

        return patient;
    }

    static public PatientRepresentation getPatientRepresentation(Patient patient){
        PatientRepresentation patientRepresentation = new PatientRepresentation();

        patientRepresentation.setFirstName(patient.getFirstName());
        patientRepresentation.setLastName(patient.getLastName());
        patientRepresentation.setUserName(patient.getUserName());
        patientRepresentation.setPassword(patient.getPassword());

        patientRepresentation.setUri("http://localhost:9000/app/patient/"+patient.getId());
        return patientRepresentation;
    }

}
