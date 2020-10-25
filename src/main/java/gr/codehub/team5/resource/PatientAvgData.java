package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Get;

public interface PatientAvgData {

    @Get
    double[] getAvgData() throws NotFoundException;
}
