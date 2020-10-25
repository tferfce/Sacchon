package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.PatientDataResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PatientDataResourceImpl extends ServerResource implements PatientDataResource {

    private long id;
    private EntityManager em;

    public PatientDataResourceImpl() {
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
    public List<PatientDataRepresentation> getPatientData() throws NotFoundException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        roles.add(CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        ResourceUtils.checkRole(this, roles);
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        if (pdataList.isEmpty()) throw new NotFoundException("No Data in the list");
        List<PatientDataRepresentation> representList = new ArrayList<>();
        pdataList.forEach(patientdata-> representList.add(PatientDataRepresentation.getDataRepresentation(patientdata)));
        return representList;
    }
}
