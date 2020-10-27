package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.representation.DoctorRepresentation;
import gr.codehub.team5.resource.DoctorNoActivityResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DoctorNoActivityResourceImpl extends ServerResource implements DoctorNoActivityResource {

    private EntityManager em;
    DoctorRepository doctorRepository;
    ConsultationRepository consultationRepository;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            doctorRepository = new DoctorRepository(em);
            consultationRepository = new ConsultationRepository(em);

        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<DoctorRepresentation> getDoctorsWithNoActivity() throws NotFoundException, ParseException, IOException {
        String paramValue1 = getQueryValue("fromDate");
        String paramValue2 = getQueryValue("toDate");
        // Get all doctors ids
        List<Doctor> doctors = doctorRepository.findAll();
        List<Long> idsFromDoctor = new ArrayList<>();
        doctors.forEach((e) -> idsFromDoctor.add(e.getId()));

        // Get doctors ids in date range from Consultation
        // A) String to Json object
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String,Object> map = mapper.readValue(dates, Map.class);
//        JsonNode rootNode = mapper.readTree(dates);
        // B) Json values to Dates
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue1);
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue2);
        // C) Add 1 day to toDate
        Calendar c = Calendar.getInstance();
        c.setTime(dateTo);
        c.add(Calendar.DATE, 1);
        dateTo = c.getTime();
        // D) Find Consultation in range
        List<Consultations> consultations = consultationRepository.findByTimeRange(dateFrom, dateTo);
        List<Long> idsFromConsultations = new ArrayList<>();
        consultations.forEach((e) -> idsFromConsultations.add(e.getDocId().getId()));

        // All doctors - Doctors in Range. (Range has the doctors with activity.)
        idsFromDoctor.removeAll(idsFromConsultations);

        // Doctors with no activity
        List<Doctor> doctorsWithNoActivity = new ArrayList<>();
        idsFromDoctor.forEach((e) -> doctorsWithNoActivity.add(doctorRepository.findById(e).get()));


        List<DoctorRepresentation> doctorRepresentation = new ArrayList<>();
        doctorsWithNoActivity.forEach(doctor -> doctorRepresentation.add(DoctorRepresentation.getDoctorRepresentation(doctor)));

        return doctorRepresentation;
    }
}
