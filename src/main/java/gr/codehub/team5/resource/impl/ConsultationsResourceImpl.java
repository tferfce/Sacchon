package gr.codehub.team5.resource.impl;

import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.util.ConsulationsResource;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ConsultationsResourceImpl extends ServerResource implements ConsulationsResource {
    @Override
    public ConsultationRepresentation add(ConsultationRepresentation consultationIn) throws BadEntityException, NotFoundException {
        return null;
    }

    @Override
    public List<ConsultationRepresentation> getConsultations() throws NotFoundException {
        return null;
    }
}
