package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.security.CustomRole;
import lombok.Data;

/**
 * Representation of Patient for Front End use
 */
@Data
public class PatientRepresentation {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String userName;
    private String password;
    private CustomRole customRole;
    private String uri;

    public static Patient getPatient(PatientRepresentation patientRepresentation){
        Patient patient = new Patient();

        patient.setFirstName(patientRepresentation.getFirstName());
        patient.setLastName(patientRepresentation.getLastName());
        patient.setGender(patientRepresentation.getGender());
        patient.setUserName(patientRepresentation.getUserName());
        patient.setPassword(patientRepresentation.getPassword());

        return patient;
    }

    public static PatientRepresentation getPatientRepresentation(Patient patient){
        PatientRepresentation patientRepresentation = new PatientRepresentation();
        patientRepresentation.setId(patient.getId());
        patientRepresentation.setCustomRole(patient.getCustomRole());
        patientRepresentation.setFirstName(patient.getFirstName());
        patientRepresentation.setLastName(patient.getLastName());
        patientRepresentation.setGender(patient.getGender());
        patientRepresentation.setUserName(patient.getUserName());
        patientRepresentation.setPassword(patient.getPassword());
        patientRepresentation.setUri("http://localhost:9000/project/patient/"+patient.getId());

        return patientRepresentation;
    }
}
