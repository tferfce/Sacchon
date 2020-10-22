package gr.codehub.team5.Application;

import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.router.CustomRouter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class RestApplication extends Application {
    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("Contacts application starting...");

        EntityManager em = SacchonJpa.getEntityManager();
        em.close();


        // Attach application to http://localhost:9000/v1
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);


        c.getDefaultHost().attach("/project", new RestApplication());

        c.start();
        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/project/");


//http://localhost:9000/project/patient/1


    }



    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);

        return customRouter.createApiRouter();
    }


//    public Router publicResources() {
//        Router router = new Router();
//        //apis
//        router.attach("/patient", PatientListResourceImpl.class);
//        router.attach("/patient/", PatientListResourceImpl.class);
//        router.attach("/patient/{id}", PatientResourceImpl.class);
//        return router;
//    }
}
