package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.util.PatientsNoActivityResource;
import org.json.JSONObject;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<PatientRepresentation> getPatientsWithNoActivity(String dates)  throws NotFoundException, ParseException {

        JSONObject datesJsonObj= new JSONObject(dates);

        String dateFromStr = datesJsonObj.getString("fromDate");
        SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateFrom= sdfFrom.parse(dateFromStr);

        String dateToStr =datesJsonObj.getString("toDate");
        SimpleDateFormat sdfTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTo = sdfTo.parse(dateToStr);

        List<Patient> patients= patientRepository.findAll();

        List<Patient> activePatients =new ArrayList<>();

        List <PatientData> patientData = patientDataRepository.findOverTimeRange(dateFrom, dateTo);

        patientData.forEach(pd -> activePatients.add(pd.getPData()));
        patients.removeAll(activePatients); //patients with no activity

        List <PatientRepresentation> inactivePatients = new ArrayList<>();

        patients.forEach(patient -> inactivePatients.add(PatientRepresentation.getPatientRepresentation(patient)) );

        return inactivePatients;
    }


}
