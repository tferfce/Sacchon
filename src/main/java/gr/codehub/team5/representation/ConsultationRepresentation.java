package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import lombok.Data;

import java.util.Date;

/**
 * Representation of Consultations for Front End use
 */
@Data
public class ConsultationRepresentation {
    private long id;
    private String consult;
    private Doctor doctor;
    private Patient patient;
    private long doctorId;
    private long patientId;
    private Date date= new Date();


    public static Consultations getConsultation(ConsultationRepresentation consultationRepresentation){
        Consultations consultation = new Consultations();
        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDocId(consultationRepresentation.getDoctor());
        consultation.setPatId(consultationRepresentation.getPatient());
        consultation.setDate(consultationRepresentation.getDate());
        return consultation;
    }


    public static ConsultationRepresentation getConsultationRepresentation(Consultations consultation){
        ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation();
        consultationRepresentation.setConsult(consultation.getConsult());
        consultationRepresentation.setId(consultation.getId());

        consultationRepresentation.setDoctorId(consultation.getDocId().getId());
        consultationRepresentation.setPatientId(consultation.getPatId().getId());
        consultationRepresentation.setDate(consultation.getDate());
        return consultationRepresentation;
    }



}
