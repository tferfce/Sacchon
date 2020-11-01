package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.representation.DoctorRepresentation;
import gr.codehub.team5.resource.DoctorNoActivityResource;
import gr.codehub.team5.resource.util.ResourceUtils;
import gr.codehub.team5.security.CustomRole;
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
        ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        String paramValue1 = getQueryValue("fromDate");
        String paramValue2 = getQueryValue("toDate");
        List<Doctor> doctors = doctorRepository.findAll();
        List<Long> idsFromDoctor = new ArrayList<>();
        doctors.forEach((e) -> idsFromDoctor.add(e.getId()));
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue1);
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue2);
        Calendar c = Calendar.getInstance();
        c.setTime(dateTo);
        c.add(Calendar.DATE, 1);
        dateTo = c.getTime();
        List<Consultations> consultations = consultationRepository.findByTimeRange(dateFrom, dateTo);
        List<Long> idsFromConsultations = new ArrayList<>();
        consultations.forEach((e) -> idsFromConsultations.add(e.getDocId().getId()));
        idsFromDoctor.removeAll(idsFromConsultations);
        List<Doctor> doctorsWithNoActivity = new ArrayList<>();
        idsFromDoctor.forEach((e) -> doctorsWithNoActivity.add(doctorRepository.findById(e).get()));
        List<DoctorRepresentation> doctorRepresentation = new ArrayList<>();
        doctorsWithNoActivity.forEach(doctor -> doctorRepresentation.add(DoctorRepresentation.getDoctorRepresentation(doctor)));
        if (doctorRepresentation.isEmpty()) throw new NotFoundException("No such Doctors");
        return doctorRepresentation;
    }
}
