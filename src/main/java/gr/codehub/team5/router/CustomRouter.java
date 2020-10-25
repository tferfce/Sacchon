package gr.codehub.team5.router;

import gr.codehub.team5.resource.PingServerResource;
import gr.codehub.team5.resource.impl.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter() {
        Router router = new Router(application.getContext());
//        //apis
//
//        // Patient
//        router.attach("/patient/{id}", PatientResourceImpl.class);
//        router.attach("/patient/{id}/data", PatientDataResourceImpl.class);//Admin Privilege also
//        router.attach("/patient/{id}/data/{listId}", PatientDataSpecifyResourceImpl.class);
//        router.attach("/patient/{id}/consultations", ConsultationListImpl.class);
//        router.attach("/patient/{id}/avg", PatientAvgDataImpl.class);
//
//        // Doctor
//        router.attach("/doctors", DoctorsResourceImpl.class);
//        router.attach("/doctor/{id}", DoctorResourceImpl.class);
//        router.attach("/noactivity", DoctorNoActivityResourceImpl.class);
//
//
//        // Consultation
//        router.attach("/consultations", ConsultationsResourceImpl.class);
//        router.attach("/consultation/{id}", ConsultationResourceImpl.class);
//
//        // Administrator
//        router.attach("/admin", AdministratorResourceImpl.class);
//        router.attach("/admin/{id}", AdminResourceGetImpl.class);
//        router.attach("/admin/doctor/{id}/consults", AdminConsultsForOfEachDoctorImpl.class);
//
//        router.attach("/login", LoginResourceImpl.class);
        return router;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        router.attach("/patient", PatientListResourceImpl.class);

        // Patient
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/patient/{id}/data", PatientDataResourceImpl.class);//Admin Privilege also
        router.attach("/patient/{id}/data/{listId}", PatientDataSpecifyResourceImpl.class);
        router.attach("/patient/{id}/consultations", ConsultationListImpl.class);
        router.attach("/patient/{id}/avg", PatientAvgDataImpl.class);

        // Doctor
        router.attach("/doctors", DoctorsResourceImpl.class);
        router.attach("/doctor/{id}", DoctorResourceImpl.class);
        router.attach("/noactivity", DoctorNoActivityResourceImpl.class);


        // Consultation
        router.attach("/consultations", ConsultationsResourceImpl.class);
        router.attach("/consultation/{id}", ConsultationResourceImpl.class);

        // Administrator
        router.attach("/admin", AdministratorResourceImpl.class);
        router.attach("/admin/{id}", AdminResourceGetImpl.class);
        router.attach("/admin/doctor/{id}/consults", AdminConsultsForOfEachDoctorImpl.class);

        router.attach("/login", LoginResourceImpl.class);

        return router;
    }
}
