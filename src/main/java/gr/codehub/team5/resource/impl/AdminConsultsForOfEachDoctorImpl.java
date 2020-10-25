package gr.codehub.team5.resource.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;

public class AdminConsultsForOfEachDoctorImpl extends ServerResource implements AdminConsultsForOfEachDoctor {

    private long id;
    private EntityManager em;
    private ConsultationRepository consultationRepository;

    public AdminConsultsForOfEachDoctorImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
        id=Long.parseLong(getAttribute("id"));
        consultationRepository = new ConsultationRepository(em);
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<ConsultationRepresentation> getConsultsForEveryDoc(String dates) throws NotFoundException, IOException, ParseException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_CHIEFDOCTOR.getRoleName());

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
