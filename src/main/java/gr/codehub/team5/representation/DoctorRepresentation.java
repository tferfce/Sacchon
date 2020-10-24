package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Doctor;
import lombok.Data;
@Data
public class DoctorRepresentation {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean deleted;


    private String uri;

    static public Doctor getDoctor(DoctorRepresentation doctorRepresentation){

        Doctor doctor = new Doctor();

        doctor.setFirstName(doctorRepresentation.getFirstName());
        doctor.setLastName(doctorRepresentation.getLastName());
        doctor.setUserName(doctorRepresentation.getUserName());
        doctor.setPassword(doctorRepresentation.getPassword());
        doctor.setDeleted(doctorRepresentation.isDeleted());

        return doctor;


    }

    static public DoctorRepresentation getDoctorRepresentation(Doctor doctor){

        DoctorRepresentation doctorRepresentation= new DoctorRepresentation();

        doctorRepresentation.setFirstName(doctor.getFirstName());
        doctorRepresentation.setLastName(doctor.getLastName());
        doctorRepresentation.setUserName(doctor.getUserName());
        doctorRepresentation.setLastName(doctor.getLastName());
        doctorRepresentation.setDeleted(doctor.isDeleted());

        doctorRepresentation.setUri("http://localhost:9000/project/doctor/"+doctor.getId());

        return doctorRepresentation;
    }


}
