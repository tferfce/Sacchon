package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.util.PatientListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientListResourceImpl extends ServerResource implements PatientListResource  {
    private EntityManager em;
    private PatientRepository patientRepository;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientRepository = new PatientRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }


    @Override
    public PatientRepresentation addPatient(PatientRepresentation patientIn) throws BadEntityException {

        //ResourceUtils.checkRole(this, CustomRole.ROLE_ADMIN.getRoleName());

        if (patientIn==null) throw new  BadEntityException("Null patient representation error");
        //if (customerIn.getName().equals("admin")) throw new  BadEntityException("Invalid customer name error");

        Patient patient = PatientRepresentation.getPatient( patientIn);

        patientRepository.save(patient);


        return PatientRepresentation.getPatientRepresentation(patient);
    }

    @Override
    public List<PatientRepresentation> getAllPatients() throws NotFoundException {

       // ResourceUtils.checkRole(this, CustomRole.ROLE_USER.getRoleName());
        List<Patient> patients= patientRepository.findAll();

        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();

        patients.forEach(patient -> patientRepresentationList.add(PatientRepresentation.getPatientRepresentation(patient)));

        return patientRepresentationList;

    }
}
