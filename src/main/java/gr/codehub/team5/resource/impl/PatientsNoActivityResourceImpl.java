package gr.codehub.team5.resource.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.PatientsNoActivityResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientsNoActivityResourceImpl extends ServerResource implements PatientsNoActivityResource {
    private EntityManager em;
    private PatientDataRepository patientDataRepository;
    private PatientRepository patientRepository;


    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientDataRepository = new PatientDataRepository(em);
            patientRepository= new PatientRepository(em);

        }catch (Exception e){
            throw new ResourceException(e);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }


    @Override
    public List<PatientRepresentation> getPatientsWithNoActivity(String dates) throws NotFoundException, ParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(dates, Map.class);
        JsonNode rootNode = mapper.readTree(dates);
        // B) Json values to Dates
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(rootNode.get("fromDate").asText());
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(rootNode.get("toDate").asText());
        // C) Add 1 day to toDate
        Calendar c = Calendar.getInstance();
        c.setTime(dateTo);
        c.add(Calendar.DATE, 1);
        dateTo = c.getTime();

        List<Patient> patients= patientRepository.findAll();
        List<Patient> activePatients =new ArrayList<>();
        List <PatientData> patientData = patientDataRepository.findByTimeRange(dateFrom, dateTo);
        patientData.forEach(pd -> activePatients.add(pd.getPData()));
        patients.removeAll(activePatients); //patients with no activity
        List <PatientRepresentation> inactivePatients = new ArrayList<>();
        patients.forEach(patient -> inactivePatients.add(PatientRepresentation.getPatientRepresentation(patient)) );
        return inactivePatients;
    }

}
