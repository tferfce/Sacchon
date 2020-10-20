package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.ConsultationResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class ConsultationResourceImpl extends ServerResource implements ConsultationResource {

    private ConsultationRepository consultationRepository;
    private EntityManager em;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
        }
        catch(Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException {
        if (consultationIn==null) throw new  BadEntityException("Null doctor representation error");

        Consultations consultation = ConsultationRepresentation.getConsultation(consultationIn);

        consultationRepository.save(consultation);
        return ConsultationRepresentation.getConsultationRepresentation(consultation);
    }
}
