package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {
    public static LocalDateTime convertToDateTimeFromString(String dateTime, LocalDate today) {
        LocalDateTime localDateTime;
        try{
            if(dateTime.toLowerCase().startsWith("today")){

                String[] parts = dateTime.split(" ",2);
                LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
                localDateTime = LocalDateTime.of(today,time);
            }
            else{
                localDateTime = LocalDateTime.parse(dateTime.toUpperCase(), DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
            }

        } catch (Throwable t){
            throw new RuntimeException("Unable to create date time from : ["+ dateTime.toUpperCase()+"], please enter with format [M/d/yyyy h:mm a]");
        }
        return localDateTime;
    }
}
