package patientintake;

import org.junit.jupiter.api.*;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    private ClinicCalendar calendar;

    @BeforeEach
    void init(){
        calendar = new ClinicCalendar(LocalDate.now());
    }
    @Test
     void allowEntryOfAnAppointment(){
        calendar.addAppointment("Jim","Weaver","avery","09/01/2019 2:30 pm");
        //upon adding, checking that it is indeed being added
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1,appointments.size());  //expected,value back from the system
        //checking all parts of the appointment were entered correctly
        PatientAppointment enteredAppt = appointments.get(0);
        assertAll(()->assertEquals("Jim",enteredAppt.getFirstName()),
                ()->assertEquals("Weaver",enteredAppt.getLastName()),
                ()->assertEquals(Doctor.avery,enteredAppt.getDoctor()),
                ()-> assertEquals("9/1/2019 02:30 PM",enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"))));
    }

    @Test

    void returnTrueForHasAppointmentIfAppointmentExists(){
        calendar.addAppointment("Jim","Weaver","avery","09/01/2019 2:30 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2019,9,1)));
    }

    @Test
    void returnFalseForHasAppointmentIfNoAppointmentExists(){
        assertFalse(calendar.hasAppointment(LocalDate.of(2018,9,1)));
    }


    @Nested
    @DisplayName("return Appointments correctly")
    class AppointmentsForDay {
        @Test
        @DisplayName("for today")
        void returnCurrentDayAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery", "09/28/2021 2:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "09/28/2021 3:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "09/12/2021 2:30 pm");
            assertEquals(2, calendar.getTodayAppointment().size());
            //assertIterableEquals(calendar.getTodayAppointment(), calendar.getAppointments());
        }

        @Test
        @DisplayName("for tomorrow")
        void returnTomorrowsAppointments() {
            calendar.addAppointment("Jim", "Weaver", "avery", "09/29/2021 2:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "09/29/2021 3:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "09/12/2021 2:30 pm");
            assertEquals(2, calendar.getTomorrowAppointments().size());
            //assertIterableEquals(calendar.getTodayAppointment(), calendar.getAppointments());
        }
    }

    @Nested
    @DisplayName("return Upcoming Appointments")
    class UpcomingAppointments{

        @Test
        @DisplayName("when there are no future appointments")
        void whenThereAreNone(){
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0,calendar.getUpcomingAppointments().size());
        }

        @Test
        @DisplayName("when there is a combination of past and future appointments")
        void whenThereAreSomePastAndFutureAppointments(){
            calendar.addAppointment("Jim", "Weaver", "avery", "07/09/2021 2:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "07/09/2021 3:30 pm");
            calendar.addAppointment("Jim", "Weaver", "avery", "09/30/2021 2:30 pm");
            assertEquals(1,calendar.getUpcomingAppointments().size());
        }

    }

}
