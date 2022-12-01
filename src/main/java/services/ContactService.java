package services;

import data.Contact;

public final class ContactService extends Service<Contact> {

    public void update(String id, String firstName, String lastName, String phoneNumber, String address) {
        super.update(id, new Contact(firstName, lastName, phoneNumber, address));
    }

    @Override
    public void update(String id, Contact contact) {
        super.update(id, contact);
    }
}