package gr.codehub.team5.router;

import gr.codehub.team5.resource.impl.*;
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
            //router.attach("/doctor/{id}/", PatientListResourceImpl.class);
            router.attach("/addPatientToDoctor/{doctorId}/{patientId}", PatientToDoctorResourceImpl.class );
            router.attach("/setDoctorDeleted/{id}", DoctorResourceImpl.class);
            router.attach("/addDoctor", DoctorsResourceImpl.class);
            router.attach("/updateDoctor/{id}",DoctorResourceImpl.class);
            //router.attach("/")

            router.attach("/addPatientData/{id}", PatientDataResourceImpl.class);
            router.attach("/getPatientData/{id}", PatientDataResourceImpl.class);

            router.attach("/patientsWithNoActivity", PatientsNoActivityResourceImpl.class);

            router.attach("/getAllPatients",PatientListResourceImpl.class);
            router.attach("/addPatient", PatientListResourceImpl.class);

            return router;
        }

        public Router publicResources() {
            Router router = new Router();

            router.attach("/ping", PingServerResource.class);
            return router;
        }




}
