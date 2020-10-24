package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.AdminDataForEachPatient;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AdminDataForEachPatientImpl extends ServerResource implements AdminDataForEachPatient {

    private long id;
    private EntityManager em;

    public AdminDataForEachPatientImpl() {
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
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName()) ;
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        List<PatientDataRepresentation> representList = new ArrayList<>();
        pdataList.forEach(patientdata-> representList.add(PatientDataRepresentation.getDataRepresentation(patientdata)));
        return representList;
    }
}
