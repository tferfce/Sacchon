package gr.codehub.team5.router;

import gr.codehub.team5.resource.PingServerResource;
import gr.codehub.team5.resource.impl.*;
import org.restlet.Application;
import org.restlet.routing.Router;

/**
 * All our Routes
 * createApiRouter protected by auth
 * publicResources open routes
 */
public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter() {
        Router router = new Router(application.getContext());
        //Patient
        router.attach("/patient/{id}/data", PatientDataResourceImpl.class);//Admin Privilege also
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/patient/{id}/data/edit", PatientDataSpecifyResourceImpl.class);
        router.attach("/patient/{id}/avg", PatientAvgDataImpl.class);
        router.attach("/patient/{id}/AllData", GetAllDataForPatientImpl.class);
        router.attach("/patient/{id}/consultations", ConsultationListImpl.class);
        //Doctor
        router.attach("/addPatientToDoctor/{doctorId}/{patientId}", PatientToDoctorResourceImpl.class );
        router.attach("/doctor/{id}/myPatients", GetPatientsForASpecificDoctorImpl.class);
        router.attach("/newPatients", GetNewPatientsImpl.class);
        router.attach("/doctors", DoctorsResourceImpl.class);
        router.attach("/doctorsPatientsWaitForConsult/{doctorId}", DoctorsPatientsWaitForConsultResourceImpl.class);
        router.attach("/doctor/{id}", DoctorResourceImpl.class);

        router.attach("/noactivity", DoctorNoActivityResourceImpl.class);

        router.attach("/consultations", ConsultationsResourceImpl.class);
        router.attach("/consultation/{id}", ConsultationResourceImpl.class);
        //Admin
        router.attach("/patientsWithNoActivity", PatientsNoActivityResourceImpl.class);
        router.attach("/allPatientsWaitForConsult", AllPatientsWaitForConsultResourceImpl.class);
        router.attach("/admin/{id}", AdminResourceGetImpl.class);
        router.attach("/admin/doctor/{id}/consults", AdminConsultsForOfEachDoctorImpl.class);


        router.attach("/patient/AllPatients/get", AllPatientsListImpl.class);
        router.attach("/login", LoginResourceImpl.class);
        return router;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        router.attach("/patient", PatientListResourceImpl.class);
        router.attach("/admin", AdministratorResourceImpl.class);

        return router;
    }
}
