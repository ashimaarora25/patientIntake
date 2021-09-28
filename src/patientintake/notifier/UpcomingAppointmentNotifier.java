package patientintake.notifier;

import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

    private ClinicCalendar calendar;

    public UpcomingAppointmentNotifier(ClinicCalendar calendar){
        this.calendar = calendar;
    }

    public void run(){
        for(PatientAppointment appt: calendar.getTomorrowAppointments()){
            SmtpMessageSender notifier = new SmtpMessageSender();
            String email = appt.getLastName().toLowerCase()+ appt.getFirstName().toLowerCase() + appt.getDoctor().getName();
            System.out.println("Sending with body: "+buildMessageBody(appt));
            notifier.sendNotification("Appointment Reminder", buildMessageBody(appt),email);


        }
    }

    private String buildMessageBody(PatientAppointment appt){
        return "You have an appointent tommorrow at "
                + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
                + " with Dr. "
                + appt.getDoctor().getName()+".";
    }


}
