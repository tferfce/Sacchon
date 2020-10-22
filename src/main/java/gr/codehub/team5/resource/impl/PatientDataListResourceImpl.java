package gr.codehub.team5.resource.impl;

import gr.codehub.team5.Model.PatientData;
import gr.codehub.team5.exceptions.BadEntityException;
import gr.codehub.team5.exceptions.NotFoundException;
import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.repository.PatientDataRepository;
import gr.codehub.team5.representation.PatientDataRepresentation;
import gr.codehub.team5.resource.util.PatientDataListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class PatientDataListResourceImpl extends ServerResource implements PatientDataListResource {

    private EntityManager em;
    private PatientDataRepository patientDataListRepository;

    @Override
    protected void doInit() throws ResourceException {
        try {
            em = SacchonJpa.getEntityManager();
            patientDataListRepository= new PatientDataRepository(em);
        }catch (Exception e){
            throw new ResourceException(e);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        em.close();
    }

    @Override
    public List<PatientDataRepresentation> getAllPatientData() throws NotFoundException {

        List<PatientData> AllPatientData= patientDataListRepository.findAll();

        List<PatientDataRepresentation> patientDataRepresentationList = new ArrayList<>();

        AllPatientData.forEach( patientData-> patientDataRepresentationList.add(PatientDataRepresentation.getPatientDataRepresentation(patientData)));

        return patientDataRepresentationList;


    }

    @Override
    public PatientDataRepresentation addPatientData(PatientDataRepresentation patientDataIn) throws BadEntityException {
        if (patientDataIn==null) throw new  BadEntityException("Null patient representation error");
        //if (customerIn.getName().equals("admin")) throw new  BadEntityException("Invalid customer name error");

        PatientData patientdata = PatientDataRepresentation.getPatientData(patientDataIn);


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
