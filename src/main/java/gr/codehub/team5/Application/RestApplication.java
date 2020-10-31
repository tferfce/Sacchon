package gr.codehub.team5.Application;

import gr.codehub.team5.jpa.SacchonJpa;
import gr.codehub.team5.router.CustomRouter;
import gr.codehub.team5.security.CustomRole;
import gr.codehub.team5.security.Shield;
import gr.codehub.team5.security.cors.CustomCorsFilter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Starting our Rest application in port 9000
 * Inserting Cors filter
 */
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
        LOGGER.info("URL: http://localhost:9000/");
    }

    public RestApplication() {

        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, CustomRole.ROLE_PATIENT.getRoleName()));
        getRoles().add(new Role(this, CustomRole.ROLE_DOCTOR.getRoleName()));
        getRoles().add(new Role(this,  CustomRole.ROLE_CHIEFDOCTOR.getRoleName()));

    }

    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);

        Router publicRouter = customRouter.publicResources();
        ChallengeAuthenticator apiGuard = shield.createApiGuard();
        // Create the api router, protected by a guard

        Router apiRouter = customRouter.createApiRouter();
        apiGuard.setNext(apiRouter);

        publicRouter.attachDefault(apiGuard);

        // return publicRouter;

        CustomCorsFilter corsFilter = new CustomCorsFilter(this);

        return corsFilter.createCorsFilter(publicRouter);
    }



}
