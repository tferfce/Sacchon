package gr.codehub.team5.router;

import gr.codehub.team5.resource.PingServerResource;
import gr.codehub.team5.resource.impl.ConsultationResourceImpl;
import gr.codehub.team5.resource.impl.DoctorResourceImpl;
import gr.codehub.team5.resource.impl.PatientListResourceImpl;
import gr.codehub.team5.resource.impl.PatientResourceImpl;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter() {
        Router router = new Router(application.getContext());
        //apis
        router.attach("/patient", PatientListResourceImpl.class);
        router.attach("/patient/", PatientListResourceImpl.class);
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/doctor", DoctorResourceImpl.class);
        router.attach("/consultation", ConsultationResourceImpl.class);
        return router;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        return router;
    }
}
