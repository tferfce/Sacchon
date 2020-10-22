package gr.codehub.team5.router;

import gr.codehub.team5.resource.impl.DoctorResourceImpl;
import gr.codehub.team5.resource.impl.PatientDataListResourceImpl;
import gr.codehub.team5.resource.impl.PatientListResourceImpl;
import gr.codehub.team5.resource.util.PingServerResource;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {



        private Application application;

        public CustomRouter(Application application) {
            this.application = application;

        }


        public Router createApiRouter() {

            Router router = new Router(application.getContext());

            router.attach("/doctor/{id}", DoctorResourceImpl.class);
            router.attach("/doctor/{doctorId}/{patientId}/data", PatientDataListResourceImpl.class);
            router.attach("/doctor/pats", PatientListResourceImpl.class);
            router.attach("/patient/",PatientListResourceImpl.class);
            router.attach("/patient/{doctorId}", PatientListResourceImpl.class);



            return router;
        }

        public Router publicResources() {
            Router router = new Router();
            router.attach("/ping", PingServerResource.class);
            return router;
        }




}
