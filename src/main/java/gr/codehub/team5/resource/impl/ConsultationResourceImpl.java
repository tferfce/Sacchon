package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.ConsultationResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ConsultationResourceImpl extends ServerResource implements ConsultationResource {

    private ConsultationRepository consultationRepository;
    private EntityManager em;
    private long id;

    @Override
    protected void doInit() {
        try {
            em = SacchonJpa.getEntityManager();
            consultationRepository = new ConsultationRepository(em);
            id = Long.parseLong(getAttribute("id"));
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
    public ConsultationRepresentation getConsultation() throws NotFoundException, ResourceException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_DOCTOR.getRoleName());
        Optional<Consultations> consultation = consultationRepository.findById(id);
        setExisting(consultation.isPresent());
        if (!consultation.isPresent())  throw new NotFoundException("Consultation is not found");
        ConsultationRepresentation consultationRepresentation = ConsultationRepresentation.getConsultationRepresentation(consultation.get());
        return consultationRepresentation;
    }

    @Override
    public ConsultationRepresentation update(ConsultationRepresentation consultationReprIn) throws NotFoundException, BadEntityException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_DOCTOR.getRoleName());
        Optional<Consultations> consultationOpt = consultationRepository.findById(id);
        if (!consultationOpt.isPresent()) throw new NotFoundException("The given consultation id is not existing");
        Consultations consultation = consultationOpt.get();

        consultation.setConsult(consultationReprIn.getConsult());
        consultation.setDate(consultationReprIn.getDate());

        consultationRepository.save(consultation);
        return ConsultationRepresentation.getConsultationRepresentation(consultation);
    }
}
