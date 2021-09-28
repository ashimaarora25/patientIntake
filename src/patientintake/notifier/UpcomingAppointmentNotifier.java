package patientintake.notifier;

import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

    private ClinicCalendar calendar;
    private EmailNotification notifier; //dependency injection

    public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotification notifier){
        this.calendar = calendar;
        this.notifier = notifier;
    }

    public void run(){
        for(PatientAppointment appt: calendar.getTomorrowAppointments()){

            String email = appt.getLastName().toLowerCase()+ appt.getFirstName().toLowerCase() + "@mail.com";
            System.out.println("Sending with body: "+buildMessageBody(appt));
            notifier.sendNotification("Appointment Reminder", buildMessageBody(appt),email);


        }
    }

    private String buildMessageBody(PatientAppointment appt){
        return "You have an appointment tomorrow at "
                + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
                + " with Dr. "
                + appt.getDoctor().getName()+".";
    }


}
