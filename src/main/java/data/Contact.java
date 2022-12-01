package data;
// how do we use regex to enforce the field requirements?
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;

import validations.Validations;

import java.util.Objects;

public final class Contact extends ServiceData{
    // Fields
    /**
     * Requirement: 10 chars max, not null, not empty
     */
    private String firstName;

    /**
     * Requirement: 10 chars max, not null, not empty
     */
    private String lastName;

    /**
     * Requirement: digits only, exactly 10 chars long, not null, not empty
     */
    private String phone;

    /**
     * Requirement: 30 chars long, not null, not empty
     */
    private String address;

    // Constructor
    // To ensure fields follow requirements, we force initialization using one constructor
    public Contact(final String firstName, final String lastName, final String phoneNumber, final String address) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }

    // Setters
    public void setFirstName(final String firstName) {
        Validations.validateObjectNotNull(firstName);
        Validations.validateStringLengthIsInRange(firstName, 10);
        // if no exception is thrown, proceed with assignment
        this.firstName = firstName;
    }
    public void setLastName(final String lastName) {
        Validations.validateObjectNotNull(lastName);
        Validations.validateStringLengthIsInRange(lastName, 10);
        this.lastName = lastName;
    }
    public void setPhoneNumber(final String phoneNumber) {
        Validations.validateObjectNotNull(phoneNumber);
        Validations.validateStringLengthEquals(phoneNumber, 10);
        Validations.validateStringContainsAllDigits(phoneNumber);
        this.phone = phoneNumber;
    }
    public void setAddress(final String address) {
        Validations.validateObjectNotNull(address);
        Validations.validateStringLengthIsInRange(address, 30);
        this.address = address;
    }

    // Getters
    public String firstName() {
        return this.firstName;
    }
    public String lastName() {
        return this.lastName;
    }
    public String phone() {
        return this.phone;
    }
    public String address() {
        return this.address;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName
                + "\nPhone Number: " + phone + "\nAddress: " + address;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == null) {
            return false;
        }
        return (obj instanceof Contact)
                && (this.firstName.equals(((Contact) obj).firstName))
                && (this.lastName.equals(((Contact) obj).lastName))
                && (this.phone.equals(((Contact) obj).phone))
                && (this.address.equals(((Contact) obj).address));
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, address);
    }
}