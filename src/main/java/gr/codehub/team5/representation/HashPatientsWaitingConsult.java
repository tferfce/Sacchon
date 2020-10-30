package gr.codehub.team5.representation;
import lombok.Data;

@Data
public class HashPatientsWaitingConsult {
    private long id;
    private String firstName;
    private String lastName;
    private long daysWaiting;


    public static HashPatientsWaitingConsult getPatientHashed(PatientRepresentation patientRepresentation,Long days){
        HashPatientsWaitingConsult hashPatientsWaitingConsult = new HashPatientsWaitingConsult();
        hashPatientsWaitingConsult.setFirstName(patientRepresentation.getFirstName());
        hashPatientsWaitingConsult.setLastName(patientRepresentation.getLastName());
        hashPatientsWaitingConsult.setId(patientRepresentation.getId());
        hashPatientsWaitingConsult.setDaysWaiting(days);
        return hashPatientsWaitingConsult;
    }
}
