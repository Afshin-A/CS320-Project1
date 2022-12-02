package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    Contact contact;

    @BeforeEach()
    void init() {
        contact = new Contact("TestName1", "TestName2", "0000000000", "1234 Test Address Rd");
    }

    @Test
    @DisplayName("Address string not empty test")
    void addressStringEmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(""),
                "String length should be > 0");
    }

    @Test
    void addressNullTest() {
        assertThrows(IllegalArgumentException.class, ()->contact.setAddress(null));
    }

    @Test
    @DisplayName("Address max length test")
    void addressMaxLengthTest() {
        String longAddress = "1600 Pennsylvania Ave., NW Washington, DC 20500";
        assertTrue(longAddress.length() > 30, "Test requires a string longer than 30 characters");
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(longAddress), "String length should be <= 30");
    }

    @Test
    void phoneStringEmptyTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setPhoneNumber(""),
                "String length should be > 0");
    }

    @Test
    void phoneNullTest() {
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(null));
    }

    @Test
    void phoneTooLongLengthTest() {
        String longPhoneNumber = "01234567891";
        assertTrue(longPhoneNumber.length() > 10);
        assertThrows(IllegalArgumentException.class, ()-> contact.setPhoneNumber(longPhoneNumber),
                "String length should == 10");
    }

    @Test
    void phoneTooShortLengthTest() {
        String shortPhoneNumber = "012345678";
        assertTrue(shortPhoneNumber.length() < 10);
        assertThrows(IllegalArgumentException.class, ()-> contact.setPhoneNumber(shortPhoneNumber),
                "String length should be == 10");
    }

    @Test
    void phoneCorrectLengthTest() {
        assertDoesNotThrow(()->contact.setPhoneNumber("0123456789"));
    }

    @Test
    void phoneDigitTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setPhoneNumber("123g567421"),
                "String should only contain digits");
    }

    @Test
    void lastNameNullTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setLastName(null),
                "Argument is null and should cause IllegalArgumentException");
    }

    @Test
    void lastNameEmptyTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setLastName(""),
                "String length should be > 0");
    }

    @Test
    void lastNameMaxCharTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setLastName("Blankenship"),
                "String length should be <= 10");
    }

    @Test
    void firstNameNullTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setFirstName(null),
                "Argument cannot be null");
    }

    @Test
    void firstNameEmptyTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setFirstName(""),
                "String length should be > 0");
    }

    @Test
    void firstNameMaxCharTest() {
        assertThrows(IllegalArgumentException.class, ()-> contact.setFirstName("Christopher"),
                "String length should be <= 10");
    }

    @Test
    void contactNotEqualsNullTest() {
        assertFalse(contact.equals(null));
    }

    @Test
    void contactEqualsItselfTest() {
        assertTrue(contact.equals(contact));
    }

    @Test
    void contactNotEqualsDifferentTypeTest() {
        assertFalse(contact.equals(""));
    }

    @Test
    void contactNotEqualNullTest() {
        assertFalse(contact.equals(null));
    }
    @Test
    void contactEqualsTest() {
        assertTrue(contact.equals(new Contact("TestName1", "TestName2", "0000000000", "1234 Test Address Rd")));
    }
}