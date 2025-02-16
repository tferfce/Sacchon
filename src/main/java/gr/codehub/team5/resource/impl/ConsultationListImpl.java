package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.ConsultationListResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ConsultationListImpl extends ServerResource implements ConsultationListResource {

    private long id;
    private EntityManager em;

    public ConsultationListImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {

        try {

            em = SacchonJpa.getEntityManager();
            id = Long.parseLong(getAttribute("id"));
        }catch (Exception ex){
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<ConsultationRepresentation> getConsults() {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        roles.add(CustomRole.ROLE_DOCTOR.getRoleName());
        ResourceUtils.checkRole(this, roles);
        TypedQuery<Consultations> query = em.createQuery("FROM Consultations C WHERE patId_id=:param", Consultations.class);
        query.setParameter("param",this.id);
        List<Consultations> consultationsList = query.getResultList();
        List<ConsultationRepresentation> representationList = new ArrayList<>();
        consultationsList.forEach(consult ->representationList.add(ConsultationRepresentation.getConsultationRepresentation(consult)));
        return representationList;
    }
}
