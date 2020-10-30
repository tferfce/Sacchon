package gr.codehub.team5.resource;

import gr.codehub.team5.exceptions.NotFoundException;
import org.restlet.resource.Get;

import java.text.ParseException;
import java.util.Map;

public interface PatientAvgData {

    @Get
    Map<String, Double> getAvgData() throws NotFoundException, ParseException;
}
