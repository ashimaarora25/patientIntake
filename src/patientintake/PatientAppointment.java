package patientintake;
import java.time.LocalDateTime;

public class PatientAppointment {

    private String firstName;
    private String lastName;
    private LocalDateTime appointmentDateTime;
    private Doctor doctor;
    private double bmi;

    public PatientAppointment(String firstName, String lastName, LocalDateTime appointmentDateTime, Doctor doctor){
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public LocalDateTime getAppointmentDateTime(){
        return this.appointmentDateTime;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setBmi(double bmi){
        this.bmi = bmi;
    }



}
