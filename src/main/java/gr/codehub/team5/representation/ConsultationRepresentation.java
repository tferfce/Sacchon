package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Consultations;
import lombok.Data;

@Data
public class ConsultationRepresentation {
    private String consult;
    private String uri;

    public static Consultations getConsultation(ConsultationRepresentation consultationRepresentation){
        Consultations consultation = new Consultations();
        consultation.setConsult(consultationRepresentation.getConsult());
        return consultation;
    }


    public static ConsultationRepresentation getConsultationRepresentation(Consultations consultation){
        ConsultationRepresentation consultationRepresentation = new ConsultationRepresentation();
        consultationRepresentation.setConsult(consultation.getConsult());
        return consultationRepresentation;
    }

}
