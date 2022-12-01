package services;

import data.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {
    AppointmentService appointmentService;

    Appointment validAppointment1;
    Appointment validAppointment2;

    String appointmentDescription1;
    String appointmentDescription2;
    Date futureDate1;
    Date futureDate2;

    @BeforeEach
    void init() {
        appointmentService = new AppointmentService();

        // 1 year in future
        futureDate1 = new Date();
        futureDate1.setYear(futureDate1.getYear() + 1);

        // 2 years in future
        futureDate2 = new Date();
        futureDate2.setYear(futureDate2.getYear() + 2);

        appointmentDescription1 = "Dentist Appointment";
        appointmentDescription2 = "Doctor's appointment";

        validAppointment1 = new Appointment(futureDate1, appointmentDescription1);
        validAppointment2 = new Appointment(futureDate2, appointmentDescription2);
    }

    @Test
    void addAppointmentWithNullIdTest() {
        assertThrows(IllegalArgumentException.class, () -> appointmentService.add(null, validAppointment1),
                "id is null so IllegalArgumentException should be thrown.");
    }

    @Test
    void addAppointmentWithEmptyIdTest() {
        assertThrows(IllegalArgumentException.class, () -> appointmentService.add("", validAppointment1),
                "id is empty, so IllegalArgumentException should be thrown.");

    }

    @Test
    void addAppointmentWithLongIdTest() {
        assertThrows(IllegalArgumentException.class, () -> appointmentService.add("123445678901", validAppointment1),
                "id is 11 chars long, so IllegalArgumentException should be thrown.");
    }

    @Test
    void addAppointmentWithPastDateTest() {
        Date pastDate = new Date();
        pastDate.setYear(pastDate.getYear() - 1);

        // It's not appointmentService.add() that is causing the exception here, it's the construction of an Appointment
        // object via new Appointment(pastDate, appointmentDescription1)
        assertThrows(IllegalArgumentException.class, () -> appointmentService.add("1", new Appointment(pastDate, appointmentDescription1)),
                "Appointment is set in the past, so IllegalArgumentException should be thrown.");
    }

    @Test
    void addAppointmentWithNullDateTest() {
        assertThrows(IllegalArgumentException.class, () -> appointmentService.add("1", null),
                "Date is null, so IllegalArgumentException should be thrown.");
    }

    @Test
    void removeAppointmentWithNonExistingIdTest() {
        appointmentService.add("1", validAppointment1);
        assertNull(appointmentService.remove("2"), "Since id=2 does not exist in database, .remove(2) should" +
                " return null");
        assertEquals(validAppointment1, appointmentService.database.get("1"), ".remove() should not affect other" +
                " map elements.");
    }

    @Test
    void removeAppointmentWithExistingIdTest() {
        appointmentService.add("1", validAppointment1);
        appointmentService.add("2", validAppointment2);
        assertEquals(appointmentService.remove("1"), validAppointment1, "should remove the correct Appointment object");
        assertFalse(appointmentService.database.containsKey("1"), "database should no longer contain the removed key");
        assertTrue(appointmentService.database.containsKey("2"), "should not affect other elements in database");
        assertEquals(appointmentService.database.get("2"), validAppointment2, "should not affect other elements in database");
    }

    @Test
    void addAppointmentWithValidIdAndDate() {
        assertDoesNotThrow(()->appointmentService.add("1", validAppointment1), "valid id and valid " +
                "Appointment should not cause any exceptions");
    }

    @Test
    void updateAppointmentWithNonExistingIdTest() {
        appointmentService.add("1", validAppointment1);
        appointmentService.add("2", validAppointment2);

        assertDoesNotThrow(()->appointmentService.update("3", validAppointment1),
                "updating non-existing id in database should do nothing.");
        assertNull(appointmentService.database.get("3"),
                "updating non-existing id should not add anything into the database");
    }

    @Test
    void updateAppointmentWithExistingIdTest() {
        appointmentService.add("1", validAppointment1);
        appointmentService.update("1", validAppointment2);
        assertEquals(validAppointment2, appointmentService.database.get("1"));
    }
}