package patientintake.notifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;
import patientintake.Doctor;
import patientintake.PatientAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UpcomingAppointmentNotifierShould {

    private EmailNotifierTestDouble emailDouble; //creating a private TestDouble object

    @BeforeEach
    void init(){
        emailDouble = new EmailNotifierTestDouble();
    }
    @Test
   void sendNotificationWithCorrectFormat(){
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2021,9,8));
        PatientAppointment appt = new PatientAppointment("Jim","Weaver", LocalDateTime.of(2021,9,9, 15,30), Doctor.avery);
        calendar.addAppointment("Jim","Weaver","avery","09/09/2021 3:30 pm");
        UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);
        notifier.run();

        assertEquals(1, emailDouble.receivedMessages.size());
        EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
        assertAll(
                ()-> assertEquals("weaverjim@mail.com", expectedMessage.toAddress),
                ()-> assertEquals("Appointment Reminder", expectedMessage.subject),
                ()-> assertEquals("You have an appointment tomorrow at 3:30 PM"+" with Dr. Ralph Avery.", expectedMessage.body)
        );



    }

}