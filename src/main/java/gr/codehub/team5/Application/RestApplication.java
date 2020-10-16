package gr.codehub.team5.Application;

import gr.codehub.team5.service.DoctorController;
import gr.codehub.team5.service.PatientController;
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
        LOGGER.info("Contacts application starting...");


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
        router.attach("/doctor", DoctorController.class);
        router.attach("/patient", PatientController.class);
        return router;
    }
}
