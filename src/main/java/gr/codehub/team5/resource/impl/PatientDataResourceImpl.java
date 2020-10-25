package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.Patient;
import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.repository.PatientRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.util.PatientDataResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDataResourceImpl extends ServerResource implements PatientDataResource {

    private EntityManager em;
    private PatientDataRepository patientDataListRepository;
    private PatientRepository patientRepository;
    private long id;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientDataListRepository= new PatientDataRepository(em);
            patientRepository = new PatientRepository(em);
            id=Long.parseLong(getAttribute("id"));
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }


    @Override
    public List<PatientDataRepresentation> getPatientData() throws NotFoundException {
        TypedQuery<PatientData> query = em.createQuery("FROM PatientData P WHERE pData_id=:param", PatientData.class);
        query.setParameter("param",this.id);
        List<PatientData> pdataList = query.getResultList();
        List<PatientDataRepresentation> representList = new ArrayList<>();
        pdataList.forEach(patientdata-> representList.add(PatientDataRepresentation.getPatientDataRepresentation(patientdata)));
        return representList;
    }

    @Override
    public PatientDataRepresentation addPatientData(PatientDataRepresentation patientDataIn) throws BadEntityException {

        if (patientDataIn==null) throw new  BadEntityException("Null patient representation error");
        //if (customerIn.getName().equals("admin")) throw new  BadEntityException("Invalid customer name error");

        PatientData patientdata = PatientDataRepresentation.getPatientData(patientDataIn);
        Optional<Patient> patient = patientRepository.findById(id);
        patientdata.setPData(patient.get());

        patientDataListRepository.save(patientdata);

        return PatientDataRepresentation.getPatientDataRepresentation(patientdata);
    }

//    @Override
//    public PatientDataRepresentation update(PatientDataRepresentation patientDataRepresentation) throws NotFoundException, BadEntityException {
//
//        return null;
//    }
//
//    @Override
//    public PatientDataRepresentation remove(PatientDataRepresentation patientDataRepresentation) throws NotFoundException, BadEntityException {
//        return null;
//    }
}
