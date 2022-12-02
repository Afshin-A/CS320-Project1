package data;

import validations.Validations;

/**
 * Proposed way to group the data classes <br>
 * Should this be an abstract class or an interface? <br>
 * What is the difference between the two?
 */
public abstract class ServiceData {
    /**
     * Ensures a String field is not null, not empty and at most maxLength chars long
     * @param field
     * @param maxLength Maximum length allowed for the String argument
     */
    protected void validateField(String field, int maxLength) {
        Validations.validateObjectNotNull(field);
        Validations.validateStringLengthIsInRange(field, maxLength);
    }
}
