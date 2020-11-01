package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.PatientDataResource;
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

public class PatientDataResourceImpl extends ServerResource implements PatientDataResource {

    private long id;
    private EntityManager em;
    private PatientDataRepository patientDataRepository;

    public PatientDataResourceImpl() {
        super();
    }

    @Override
    protected void doInit() throws ResourceException {
        try {

            em = SacchonJpa.getEntityManager();
            id = Long.parseLong(getAttribute("id"));
            patientDataRepository = new PatientDataRepository(em);
        }catch (ResourceException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<PatientDataRepresentation> getPatientData() throws NotFoundException, IOException, ParseException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_PATIENT.getRoleName());
        roles.add(CustomRole.ROLE_CHIEFDOCTOR.getRoleName());
        ResourceUtils.checkRole(this, roles);
        String paramValue1=getQueryValue("fromDate");
        String paramValue2=getQueryValue("toDate");
        Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue1);
        Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(paramValue2);
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
        if (pdataList.isEmpty()) throw new NotFoundException("No Data in the list");
        List<PatientDataRepresentation> representList = new ArrayList<>();
        pdataList.forEach(patientdata-> representList.add(PatientDataRepresentation.getDataRepresentation(patientdata)));
        return representList;
    }
}
