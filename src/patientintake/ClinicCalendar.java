package patientintake;

//schedule for the clinic
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicCalendar {

    private List<PatientAppointment> appointments;
    private LocalDate today;


    public ClinicCalendar(LocalDate date){

        this.appointments = new ArrayList<>();
        this.today=date;
    }

    public void addAppointment(String firstName, String lastName, String doctorKey, String dateTime) {

        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = DateTimeConverter.convertToDateTimeFromString(dateTime, today);
        PatientAppointment appointment = new PatientAppointment(firstName, lastName, localDateTime, doc);
        appointments.add(appointment);
    }



    public List<PatientAppointment> getAppointments(){
        return this.appointments;
    }

    public List<PatientAppointment> getTodayAppointment(){
        return appointments.stream().filter(appt->appt.getAppointmentDateTime().toLocalDate().equals(today)).collect(Collectors.toList());
    }
    public List<PatientAppointment> getTomorrowAppointments(){
        return appointments.stream().filter(appt->appt.getAppointmentDateTime().toLocalDate().equals(today.plusDays(1))).collect(Collectors.toList());
    }

    public boolean hasAppointment(LocalDate date){
        return appointments.stream().anyMatch(appt->appt.getAppointmentDateTime().toLocalDate().equals(date));

    }


    public List<PatientAppointment> getUpcomingAppointments() {
        return appointments.stream().filter(appt->appt.getAppointmentDateTime().toLocalDate().isAfter(today)).collect(Collectors.toList());
    }
}
