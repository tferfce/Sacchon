package gr.codehub.team5.resource;

import org.restlet.resource.Get;

public interface PatientAvgData {

    @Get
    double[] getAvgData();
}
