package gr.codehub.team5.Application;

import gr.codehub.team5.resource.impl.*;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;

import java.util.logging.Logger;

public class RestApplication extends Application {
    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Patients application starting...");

        // Attach application to http://localhost:9000/v1
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/project", new RestApplication());
        c.start();
        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/");
    }

    @Override
    public Restlet createInboundRoot() {

        Router publicRouter = publicResources();

        // Create the api router, protected by a guard

        return publicRouter;
    }


    public Router publicResources() {
        Router router = new Router();
        //apis
        router.attach("/patient", PatientListResourceImpl.class);
        router.attach("/patient/", PatientListResourceImpl.class);
        router.attach("/patient/{id}", PatientResourceImpl.class);
        router.attach("/patient/{id}/data", PatientDataResourceImpl.class);
        router.attach("/patient/{id}/data/{listId}", PatientDataSpecifyResourceImpl.class);
        router.attach("/patient/{id}/consultations", ConsultationListImpl.class);
        return router;
    }
}
