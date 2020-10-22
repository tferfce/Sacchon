package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.ConsultationListResource;
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

        em = SacchonJpa.getEntityManager();
        id=Long.parseLong(getAttribute("id"));
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<ConsultationRepresentation> getConsults() throws NotFoundException {
        TypedQuery<Consultations> query = em.createQuery("FROM Consultations C WHERE patId_id=:param", Consultations.class);
        query.setParameter("param",this.id);
        List<Consultations> consultationsList = query.getResultList();
        List<ConsultationRepresentation> representationList = new ArrayList<>();
        consultationsList.forEach(consult ->representationList.add(ConsultationRepresentation.getConsultationRepresentation(consult)));
        return representationList;
    }
}
