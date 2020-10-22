package gr.codehub.team5.resource.util;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PingServerResource extends ServerResource {
    public static final String PING = "Version: 1.0.0 running";


    @Get("txt")
    public String ping() {
        return PING;
    }

}
