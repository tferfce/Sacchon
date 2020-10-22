package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Doctor;
import lombok.Data;

@Data
public class DoctorRepresentation {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    private String uri;

    //Creates Doctor from DoctorRepresentation
    public static Doctor getDoctor(DoctorRepresentation doctorRepresentation){
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorRepresentation.getFirstName());
        doctor.setLastName(doctorRepresentation.getLastName());
        doctor.setUserName(doctorRepresentation.getUserName());
        doctor.setPassword(doctorRepresentation.getPassword());
        return doctor;
    }

    //Creates DoctorRepresentation from Doctor
    public static DoctorRepresentation getDoctorRepresentation(Doctor doctor){
        DoctorRepresentation doctorRepresentation = new DoctorRepresentation();
        doctorRepresentation.setFirstName(doctor.getFirstName());
        doctorRepresentation.setLastName(doctor.getLastName());
        doctorRepresentation.setUserName(doctor.getUserName());
        doctorRepresentation.setPassword(doctor.getPassword());
        return doctorRepresentation;

    }
}
