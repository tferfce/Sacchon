package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Consultations;
import lombok.Data;

@Data
public class ConsultationRepresentation {

    private String consult;
//    private Doctor doctor;
//    private Patient patient;

    private String uri;

    //Creates Consultation from ConsultationRepresentation
    public static Consultations getConsultation(ConsultationRepresentation consultationRepresentation){
        Consultations consultation = new Consultations();
        consultation.setConsult(consultationRepresentation.getConsult());
//        consultation.setDocId(consultationRepresentation.getDoctor());
//        consultation.setPatId(consultationRepresentation.getPatient());
        return consultation;
    }

    //Creates ConsultationRepresentation from Consultation
    public static ConsultationRepresentation getConsultationRepresentation(Consultations consultation){
        ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation();
        consultationRepresentation.setConsult(consultation.getConsult());
//        consultationRepresentation.setDoctor(consultation.getDocId());
//        consultationRepresentation.setPatient(consultation.getPatId());
        return consultationRepresentation;
    }
}
