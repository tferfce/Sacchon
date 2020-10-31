package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.ConsultationRepository;
import gr.codehub.team5.representation.ConsultationRepresentation;
import gr.codehub.team5.resource.AdminConsultsForOfEachDoctor;
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

public class AdminConsultsForOfEachDoctorImpl extends ServerResource implements AdminConsultsForOfEachDoctor {

    private long id;
    private EntityManager em;
    private ConsultationRepository consultationRepository;

    public AdminConsultsForOfEachDoctorImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            id = Long.parseLong(getAttribute("id"));
            consultationRepository = new ConsultationRepository(em);
        }catch (ResourceException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<ConsultationRepresentation> getConsultsForEveryDoc() throws NotFoundException, IOException, ParseException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());

        String paramValue1=getQueryValue("fromDate");
        String paramValue2=getQueryValue("toDate");
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue1);
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue2);
        Calendar c = Calendar.getInstance();
        c.setTime(dateTo);
        c.add(Calendar.DATE, 1);
        dateTo = c.getTime();
        List<Consultations> allConsultsInRange = consultationRepository.findByTimeRange(dateFrom,dateTo);
        List<Consultations> consultationsList = new ArrayList<>();

        for (Consultations cons: allConsultsInRange){
            if (cons.getDocId().getId()==this.id){
                consultationsList.add(cons);
            }
        }
        if (consultationsList.isEmpty()) throw new NotFoundException("No data");
        List<ConsultationRepresentation> representationList = new ArrayList<>();
        consultationsList.forEach(consult ->representationList.add(ConsultationRepresentation.getConsultationRepresentation(consult)));
        return representationList;
    }
}
