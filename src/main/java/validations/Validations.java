package validations;

/**
 * Contains all validation rules for the project
 */
public class Validations {
    private Validations() {}

    /**
     * Used to ensure length of a given string is between 0 and max
     * @param arg String to operate on
     * @param max Maximum valid length for arg
     * @throws IllegalArgumentException
     */
    public static void validateStringLengthIsInRange(final String arg, final int max) {
        if (arg.length() == 0 || arg.length() > max) {
            throw new IllegalArgumentException("Argument length is 0 or greater than " + max);
        }
    }

    /**
     * Used to ensure length of a given String equals length
     * @param arg String to operate on
     * @param length Acceptable length for arg
     * @throws IllegalArgumentException
     */
    public static void validateStringLengthEquals(final String arg, final int length) {
        if (arg.length() != length) {
            throw new IllegalArgumentException("Argument length is not " + length +  "chars long");
        }
    }

    /**
     * Ensures a given String contains digits only
     * @param arg String to operate on
     * @throws IllegalArgumentException
     */
    public static void validateStringContainsAllDigits(final String arg) {
        for (int i = 0; i < arg.length(); i++) {
            if (!Character.isDigit(arg.charAt(i))) {
                throw new IllegalArgumentException("Argument contains non-digit");
            }
        }
    }

    /**
     * Ensures an object is not null
     * @param o Object of any type
     * @throws IllegalArgumentException
     */
    public static void validateObjectNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Argument is null");
        }
    }
}
