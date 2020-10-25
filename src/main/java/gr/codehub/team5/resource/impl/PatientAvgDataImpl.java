package gr.codehub.team5.resource.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.resource.PatientAvgData;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientAvgDataImpl extends ServerResource implements PatientAvgData {

    private EntityManager em;
    private long id;
    private PatientDataRepository patientDataRepository;

    public PatientAvgDataImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        em = SacchonJpa.getEntityManager();
        id = Long.parseLong(getAttribute("id"));
        patientDataRepository = new PatientDataRepository(em);
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public double[] getAvgData(String dates) throws NotFoundException, IOException, ParseException {
        //ResourceUtils.checkRole(this, CustomRole.ROLE_PATIENT.getRoleName());

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
        List<PatientData> pdataList = new ArrayList<>();
        List<PatientData> allDataInRange = patientDataRepository.findByTimeRange(dateFrom,dateTo);
        for (PatientData p: allDataInRange){
            if (p.getPData().getId()==this.id){
                pdataList.add(p);
            }
        }
        if (pdataList.isEmpty()) throw new NotFoundException("No data");
        double totalCarbs=0;
        double totalBloodGlucose = 0;
        for (PatientData pdata: pdataList){
            totalCarbs += pdata.getCarbIntake();
            totalBloodGlucose +=pdata.getBloodGlucose();
        }
        double[] avgStatistics = new double[2];
        avgStatistics[0] = totalCarbs/pdataList.size();
        avgStatistics[1] = totalBloodGlucose/pdataList.size();
        return avgStatistics;
    }
}
