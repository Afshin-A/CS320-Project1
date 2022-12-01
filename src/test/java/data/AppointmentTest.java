package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    Date futureDate;
    Date pastDate;

    String description = "Dentist appointment";

    Appointment validAppointment;

    @BeforeEach
    void init() {
        futureDate = new Date();
        futureDate.setYear(futureDate.getYear() + 1);
        pastDate = new Date();
        pastDate.setYear(pastDate.getYear() - 1);

        validAppointment = new Appointment(futureDate, description);
    }

    @Test
    void instantiateWithNullDateTest() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, description));
    }

    @Test
    void instantiateWithPastDateTest() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(pastDate, description));
    }

    @Test
    void instantiateWithValidDateTest() {
        assertDoesNotThrow(() -> new Appointment(futureDate, description));
    }

    @Test
    void instantiateWithNullDescriptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(futureDate, null));
    }

    @Test
    void instantiateWithEmptyDescriptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(futureDate, ""));
    }

    @Test
    void instantiateWithLongDescriptionTest() {
        String longDescription = "This project manifests the importance of the planning phase in software development. " +
                "I've realised the need for refactoring after 3 weeks into the project! And I've just now proposed a new" +
                "architecture for the program using a UML diagram. That reminds me, I should add the diagram in my final report.";
        assertTrue(longDescription.length() > 50);
        assertThrows(IllegalArgumentException.class, () -> new Appointment(futureDate, longDescription));
    }

    @Test
    void instantiateWithValidDescriptionTest() {
        assertDoesNotThrow(()->new Appointment(futureDate, description));
    }

    @Test
    void appointmentNotEqualsNullTest() {
        assertFalse(validAppointment.equals(null));
    }

    @Test
    void appointmentEqualsItselfTest() {
        assertTrue(validAppointment.equals(validAppointment));
    }

    @Test
    void appointmentEqualsTest() {
        // In order for 2 Date objects to be equal, they must represent the same time, down to the millisecond.
        // Since JUnit runs the test cases in random, futureDate and futureDate2 are often not created at the same time, and
        // therefore represent different times. Thus, the following tests would fail. We must therefore take a different approach
        // to make the different Date objects equal.

        long milliseconds = futureDate.getTime();
        Date futureDate2 = new Date();
        futureDate2.setTime(milliseconds);
        Appointment validAppointment2 = new Appointment(futureDate2, description);

        assertTrue(validAppointment.equals(validAppointment2));
        assertTrue(validAppointment2.equals(validAppointment));
    }
}