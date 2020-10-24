package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class ConsultationRepresentation {

    private String consult;
    private Doctor doctor;
    private Patient patient;
    private long doctorId;
    private long patientId;
    private Date date = new Date();

    private String uri;

    //Creates Consultation from ConsultationRepresentation
    public static Consultations getConsultation(ConsultationRepresentation consultationRepresentation){
        Consultations consultation = new Consultations();
        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDocId(consultationRepresentation.getDoctor());
        consultation.setPatId(consultationRepresentation.getPatient());
        consultation.setDate(consultationRepresentation.getDate());
        return consultation;
    }

    //Creates ConsultationRepresentation from Consultation
    public static ConsultationRepresentation getConsultationRepresentation(Consultations consultation){
        ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation();
        consultationRepresentation.setConsult(consultation.getConsult());
        //consultationRepresentation.setDoctor(consultation.getDocId());
        consultationRepresentation.setDoctorId(consultation.getDocId().getId());
        consultationRepresentation.setPatientId(consultation.getPatId().getId());
        consultationRepresentation.setDate(consultation.getDate());
        //consultationRepresentation.setUri("http://localhost:9000/app/consultation/"+consultation.getDocId()+"/"+consultation.getPatId());
        return consultationRepresentation;
    }

    // TO DO: After merge
    // public static Consultations getConsultation(ConsultationRepresentation consultationRepresentation){
    //     Consultations consultation = new Consultations();
    //     consultation.setConsult(consultationRepresentation.getConsult());
    //     return consultation;
    // }


    // public static ConsultationRepresentation getConsultationRepresentation(Consultations consultation){
    //     ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation();
    //     consultationRepresentation.setConsult(consultation.getConsult());
    //     return consultationRepresentation;
    // }

}
