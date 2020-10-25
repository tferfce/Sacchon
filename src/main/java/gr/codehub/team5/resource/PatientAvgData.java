package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Get;

import java.io.IOException;
import java.text.ParseException;

public interface PatientAvgData {

    @Get
    double[] getAvgData(String dates) throws NotFoundException, IOException, ParseException;
}
