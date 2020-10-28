package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Consultations;
import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.DoctorRepository;
import gr.codehub.team5.representation.PatientRepresentation;
import gr.codehub.team5.resource.DoctorsPatientsWaitForConsultResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DoctorsPatientsWaitForConsultResourceImpl extends ServerResource implements DoctorsPatientsWaitForConsultResource {

    private EntityManager em;
    private DoctorRepository doctorRepository;

    private long doctorId;



    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            doctorRepository= new DoctorRepository(em);
            doctorId=Long.parseLong(getAttribute("doctorId"));
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }

    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<PatientRepresentation> getDoctorsPatientsWaitForConsult() throws ResourceException, BadEntityException, NotFoundException {

        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (!doctorOpt.isPresent()) throw new NotFoundException("The given doctor id is not existing");
        Doctor doctor = doctorOpt.get();
        TypedQuery<Patient> query= em.createQuery("FROM Patient p WHERE doctor_id=:param OR doctor_id= NULL", Patient.class);
        query.setParameter("param", doctorId);
        List<Patient> patients= query.getResultList();
        List<PatientRepresentation> patientsWaitForConsult= new ArrayList<>();

        for(Patient patient: patients) {
            List<Consultations> consults = getConsultsQueryResult(patient);
            if (consults.size() == 0) {
                List<PatientData> pdataList = getPatientQueryResult(patient);
                if (pdataList.size() == 0) {
                    System.out.println("The patient hasn't entered any data.");
                } else {
                    Date fromDate = pdataList.get(0).getDate();
                    patientsWaitForConsult= checkdiff(fromDate, patientsWaitForConsult, patient);
                }
            } else {
                Date lastConsultDate = consults.get(consults.size() - 1).getDate();
                patientsWaitForConsult= checkdiff(lastConsultDate, patientsWaitForConsult, patient);
            }

        }

        return patientsWaitForConsult;
    }

    public List<PatientData> getQueryResult(Patient patient){
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

    public List<PatientData> getPatientQueryResult(Patient patient){
        TypedQuery<PatientData> query= em.createQuery("FROM PatientData p WHERE pData_id=:param", PatientData.class);
        query.setParameter("param", patient.getId());
        List<PatientData> pdataList= query.getResultList();
        return pdataList;
    }

    public long calculatediff(Date fromDate){
        Date toDate =new Date();
        long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) +1;
        return diff;

    }
    public List<PatientRepresentation> checkdiff(Date fromDate, List<PatientRepresentation> patientsWaitForConsult, Patient patient){
        if(calculatediff(fromDate)>=30){//30 days
            Long days= calculatediff(fromDate) -30;
            patientsWaitForConsult.add(PatientRepresentation.getPatientRepresentation(patient));
        }
        return patientsWaitForConsult;
    }

}
