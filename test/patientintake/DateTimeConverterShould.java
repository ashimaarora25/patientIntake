package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dateTime")
class DateTimeConverterShould {

    @Nested
    @DisplayName("convert string with 'today' keyword ")
    class TodayTests{
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly(){
            LocalDate today = LocalDate.of(2021, 9, 8);
            LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("today 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2021,9,8, 13, 0), ()->"Failed to convert 'today' string to expected message, today passed was: "+today);
        }
        @Test
        @DisplayName("regardless of the case")
        void convertTodayStringCorrectlyCaseInsensitive(){
            LocalDate today = LocalDate.of(2021, 9, 8);
            LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("toDaY 1:00 pm", today);
            assertEquals(result, LocalDateTime.of(2021,9,8, 13, 0),()->"Failed to convert 'today' string to expected message, today passed was: "+today);
        }

    }



    @Test
    @DisplayName("convert expected date time pattern in string to date correctly")
    void convertCorrectPatternToDateTimeCorrectly(){
        LocalDateTime result = DateTimeConverter.convertToDateTimeFromString("9/8/2021 1:00 pm", LocalDate.of(2021,9,8));
        assertEquals(result, LocalDateTime.of(2021,9,8, 13, 0));
    }


    @Test
    @DisplayName("throw exception if entered pattern of string is incorrect")
    void throwExceptionIfIncorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, ()->DateTimeConverter.convertToDateTimeFromString("9/8/2021 100 pm", LocalDate.of(2021,9,8)));
        assertEquals("Unable to create date time from : [9/8/2021 100 PM]," + " please enter with format [M/d/yyyy h:mm a]", error.getMessage());
    }
}