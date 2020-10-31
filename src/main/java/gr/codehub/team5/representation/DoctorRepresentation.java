package gr.codehub.team5.representation;

import gr.codehub.team5.Model.Doctor;
import gr.codehub.team5.security.CustomRole;
import lombok.Data;

/**
 * Doctor Representation for Front End use
 */
@Data
public class DoctorRepresentation {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private CustomRole customRole;
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
        doctorRepresentation.setCustomRole(doctor.getCustomRole());
        doctorRepresentation.setId(doctor.getId());
        doctorRepresentation.setFirstName(doctor.getFirstName());
        doctorRepresentation.setLastName(doctor.getLastName());
        doctorRepresentation.setUserName(doctor.getUserName());
        doctorRepresentation.setPassword(doctor.getPassword());
        doctorRepresentation.setUri("http://localhost:9000/project/doctor/"+doctor.getId());
        return doctorRepresentation;

    }
}
