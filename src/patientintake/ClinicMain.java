package patientintake;


import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Optional;
import java.util.Scanner;



public class ClinicMain {
    private static ClinicCalendar calendar;

    public static void main(String[] args) throws Throwable {
        calendar = new ClinicCalendar(LocalDate.now());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Patient Intake Computer System! \n\n");
        String lastOption="";
        //do operations unless user wants to exit
        while (!lastOption.equalsIgnoreCase("x")){
            lastOption=displayMenu(scanner);
        }
        System.out.println("\nExiting System....\n");
    }




    private static String displayMenu(Scanner scanner) throws Throwable{
        System.out.println("Please select an option: ");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View today's appointments");
        System.out.println("4. Enter patient height and weight");
        System.out.println("X. Exit System");
        System.out.println("Option: ");
        String option = scanner.next();

        switch(option){
            case "1": performPatientEntry(scanner);
            return option;
            case "2": performAllAppointments();
            return option;
            case "3": performTodayAppointments();
            return option;
            case "4": performHeightWeight(scanner);
            return option;
            default: System.out.println("Invalid option, please re-enter: ");
            return option;
        }
    }

    private static void performPatientEntry(Scanner scanner){
        scanner.nextLine(); //empty line, not stored
        System.out.println("\n\nPlease Enter Appointment Info:");
        System.out.print("  Patient Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("  Patient First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Appointment Date (M/d/yyyy h:m a): ");
        String when = scanner.nextLine();
        System.out.print("DoctorLastName: ");
        String doc = scanner.nextLine();
        try{
            calendar.addAppointment(firstName, lastName, doc, when);
        } catch(Throwable t){
            System.out.println("Error! "+ t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully. \n\n");
    }


    private static void performAllAppointments() throws Throwable {
        System.out.println("\n\nAll Appointments in System:");
        for (PatientAppointment appointment: calendar.getAppointments()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(String.format("%s: %s, %s\t\tDoctor: %s",apptTime, appointment.getLastName(), appointment.getFirstName(), appointment.getDoctor().getName()));
        }
        System.out.println("\nPress any key to continue....");

    }
    private static void performTodayAppointments() throws Throwable {
        System.out.println("\n\nAppointments in System for today:");
        for (PatientAppointment appointment: calendar.getTodayAppointment()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(String.format("%s: %s, %s\t\tDoctor: %s",apptTime, appointment.getLastName(), appointment.getFirstName(), appointment.getDoctor().getName()));
        }
        System.out.println("\nPress any key to continue....");

    }

    private static void performHeightWeight(Scanner scanner){
        scanner.nextLine();
        System.out.println("\n\nEnter patient height weight for today's appointment: ");
        System.out.print(" Patient Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Patient First Name: ");
        String firstName = scanner.nextLine();

        PatientAppointment appt = findPatientAppointment(lastName,firstName).orElse(null);
        if (appt!=null){
            System.out.print(" Height in Inches: ");
            Integer inches = scanner.nextInt();
            System.out.print(" Weight in Pounds: ");
            Integer pounds = scanner.nextInt();
            double roundedToTwoPlaces = BMICalculator.calculateBmi(inches, pounds);
            appt.setBmi(roundedToTwoPlaces);
            System.out.println("Set patient BMI to "+roundedToTwoPlaces+ "\n\n");
        }
        else{
            System.out.println("Patient Not Found. \n\n");
        }

    }

    public static Optional<PatientAppointment> findPatientAppointment(String firstName, String lastName){
        return calendar.getTodayAppointment().stream().filter(p->(p.getFirstName().equalsIgnoreCase(firstName) &&
                p.getLastName().equalsIgnoreCase(lastName))).findFirst();
    }

}
