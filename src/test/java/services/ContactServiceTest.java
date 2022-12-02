package services;

import data.Contact;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    Contact contact1;
    Contact contact2;
    ContactService contactService;

    @BeforeEach()
    void initContact() {
        contact1 = new Contact("FirstName1", "LastName1", "0000000000", "1234 Test Address Rd");
        contact2 = new Contact("FirstName2", "LastName2", "0000000000", "1234 Test Address Rd");
        contactService = new ContactService();
    }

    @Test
    @DisplayName("ID null test")
    void addContactWithNullIDTest() {
        assertThrows(IllegalArgumentException.class, ()->contactService.add(null, contact1),
                "Argument cannot be null. Test should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("ID empty test")
    void addContactWithEmptyIdTest() {
        assertThrows(IllegalArgumentException.class, () -> contactService.add("", contact1),
                "String length should be > 0");
    }

    @Test
    @DisplayName("ID max length test")
    void addContactWithMaxAllowedIdLengthTest() {
        String maxID = "1234567890";
        assertTrue(maxID.length() == 10);
        assertDoesNotThrow(() -> contactService.add(maxID, contact1));
    }

    @Test
    void addContactWithLongIdLengthTest() {
        String longID = "01234567891";
        assertTrue(longID.length() > 10);
        assertThrows(IllegalArgumentException.class, () -> contactService.add(longID, contact1),
                "String length should be <= 10");
    }

    @Test
    void duplicateIDInMapTest() {
        contactService.add("1", contact1);
        contactService.add("1", contact2);
        assertFalse(contactService.database.containsValue(contact2), "Only one contact can exist per id");
    }

    @Test
    void addContactWithValidIdAndContactTest() {
        contactService.add("1", contact1);
        assertTrue(contactService.database.containsValue(contact1), "Add method should add to database");
    }


    @Test
    void removeContactWithNonExistingIdTest() {
        contactService.add("1", contact1);
        contactService.remove("2");
        assertTrue(contactService.database.containsKey("1"),
                "Attempting to remove id that does not exist in database should make no changes.");
    }

    @Test
    void removeContactWithValidIDTest() {
        contactService.add("1", contact1);
        contactService.remove("1");
        assertFalse(contactService.database.containsKey("1"), "remove method should remove id key and its values");
    }

    @Test
    void overriddenUpdateContactTest() {
        contactService.add("1", contact1);
        contactService.update("1", contact2);
        assertAll(
                () -> assertEquals(contact2.getFirstName(), contactService.database.get("1").getFirstName()),
                () -> assertEquals(contact2.getLastName(), contactService.database.get("1").getLastName()),
                () -> assertEquals(contact2.getPhone(), contactService.database.get("1").getPhone()),
                () -> assertEquals(contact2.getAddress(), contactService.database.get("1").getAddress())
        );
    }

    @Test
    void updateContactTest() {
        contactService.add("1", contact1);
        contactService.update(
                "1",
                contact2.getFirstName(),
                contact2.getLastName(),
                contact2.getPhone(),
                contact2.getAddress()
        );

        assertAll(
                () -> assertEquals(contact2.getFirstName(), contactService.database.get("1").getFirstName()),
                () -> assertEquals(contact2.getLastName(), contactService.database.get("1").getLastName()),
                () -> assertEquals(contact2.getPhone(), contactService.database.get("1").getPhone()),
                () -> assertEquals(contact2.getAddress(), contactService.database.get("1").getAddress())
        );
    }
}