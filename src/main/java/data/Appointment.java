package data;

import java.util.Date;
import java.util.Objects;

import validations.Validations;

public final class Appointment extends ServiceData {
    /**
     * Requirement: Cannot be null. Cannot be in the past.
     */
    private Date appointmentDate;
    /**
     * Requirement: Cannot be null. Cannot be longer than 50 characters.
     */
    private String appointmentDescription;

    public Appointment(Date appointmentDate, String appointmentDescription) {
        setAppointmentDate(appointmentDate);
        setAppointmentDescription(appointmentDescription);
    }

    public void setAppointmentDate(final Date appointmentDate) {
        Validations.validateObjectNotNull(appointmentDate);
        // new Date() represents now
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException();
        }
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentDescription(final String appointmentDescription) {
        Validations.validateObjectNotNull(appointmentDescription);
        Validations.validateStringLengthIsInRange(appointmentDescription, 50);
        this.appointmentDescription = appointmentDescription;
    }

    public Date getAppointmentDate() {
        return this.appointmentDate;
    }

    public String getAppointmentDescription() {
        return this.appointmentDescription;
    }

    @Override
    public String toString() {
        return "Appointment Date: " + appointmentDate + "\nAppointment Description: " + appointmentDescription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.appointmentDate, this.appointmentDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof Appointment) &&
                ((Appointment) obj).appointmentDate.equals(this.appointmentDate) &&
                ((Appointment) obj).appointmentDescription.equals(this.appointmentDescription);
    }
}