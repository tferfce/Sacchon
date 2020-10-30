package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.HashPatientsWaitingConsult;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.AllPatientsWaitForConsultResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AllPatientsWaitForConsultResourceImpl extends ServerResource implements AllPatientsWaitForConsultResource {

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
    public List<HashPatientsWaitingConsult> getAllPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException {

        List<Patient> Patients= patientRepository.findAll();
        HashMap <PatientRepresentation , Long> patientsWaitForConsult = new HashMap <>();

        for (Patient patient: Patients){
            List <Consultations> consults= getConsultsQueryResult(patient);
            if (consults.size()==0){
                List<PatientData> pdataList= getPatientQueryResult(patient);
                if (pdataList.size()!=0){
                    Date fromDate= pdataList.get(0).getDate();
                    patientsWaitForConsult= checkdiff(fromDate, patientsWaitForConsult, patient);
                }
            }
            else{
                Date lastConsultDate= consults.get(consults.size()-1).getDate();
                patientsWaitForConsult= checkdiff(lastConsultDate, patientsWaitForConsult, patient);
            }
        }
        List<HashPatientsWaitingConsult> hashedPatients = new ArrayList<>();
        for (Map.Entry<PatientRepresentation,Long> map: patientsWaitForConsult.entrySet()){
            hashedPatients.add(HashPatientsWaitingConsult.getPatientHashed(map.getKey(), map.getValue()));
        }
        if (hashedPatients.isEmpty()) throw new NotFoundException("No patients Waiting Consult");

        return hashedPatients;
    }

    public List<PatientData> getPatientQueryResult(Patient patient){
        TypedQuery<PatientData> query= em.createQuery("FROM PatientData p WHERE pData_id=:param", PatientData.class);
        query.setParameter("param", patient.getId());
        List<PatientData> pdataList= query.getResultList();
        return pdataList;
    }

    public List<Consultations> getConsultsQueryResult(Patient patient){

        TypedQuery<Consultations> query2= em.createQuery("FROM Consultations c WHERE patId_id=:param", Consultations.class);
        query2.setParameter("param", patient.getId());
        List<Consultations> consults= query2.getResultList();
        return consults;

    }

    public long calculatediff(Date fromDate){
        Date toDate =new Date();
        long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) +1;
        return diff;

    }

    public HashMap<PatientRepresentation, Long> checkdiff(Date fromDate, HashMap <PatientRepresentation , Long> patientsWaitForConsult, Patient patient){
        if(calculatediff(fromDate)>=30){//30 days
            Long days= calculatediff(fromDate) -30;
            patientsWaitForConsult.put(PatientRepresentation.getPatientRepresentation(patient), days);
        }
        return patientsWaitForConsult;
    }



}
