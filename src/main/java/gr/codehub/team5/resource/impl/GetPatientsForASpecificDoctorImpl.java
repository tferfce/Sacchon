package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.GetPatientsForASpecificDoctor;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class GetPatientsForASpecificDoctorImpl extends ServerResource implements GetPatientsForASpecificDoctor {


    private long id;
    private EntityManager em;

    public GetPatientsForASpecificDoctorImpl() {
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
    public List<PatientRepresentation> getSpecificPatients() throws NotFoundException {
        TypedQuery<Patient> query = em.createQuery("FROM Patient P WHERE doctor_id=:param", Patient.class);
        query.setParameter("param",this.id);
        List<Patient> patients = query.getResultList();
        List<PatientRepresentation> patientRepresentations = new ArrayList<>();
        patients.forEach(patient -> patientRepresentations.add(PatientRepresentation.getPatientRepresentation(patient)));
        if (patientRepresentations.isEmpty()) throw new NotFoundException("This doctor has no Patients");
        return patientRepresentations;
    }
}
