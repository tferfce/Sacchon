package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.AllPatientsList;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class AllPatientsListImpl extends ServerResource implements AllPatientsList {
    private EntityManager em;
    private PatientRepository patientRepository;

    public AllPatientsListImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
        }catch (ResourceException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }
    @Override
    public List<PatientRepresentation> getPatients() throws NotFoundException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());

        List<Patient> patients= patientRepository.findAll();
        if (patients.isEmpty()) throw new NotFoundException("No existing Patients");
        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
        patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));
        return patientRepresentationList;

    }
}
