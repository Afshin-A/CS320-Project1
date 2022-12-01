package data;

import validations.Validations;

import java.util.Objects;

public final class Task extends ServiceData {
    /**
     * Requirements: not null, not empty, less than 20 chars
     */
    private String taskName;

    /**
     * Requirements: not null, not empty, less than 50 chars
     */
    private String taskDescription;

    // Constructor
    public Task(final String taskName, final String taskDescription) {
        setTaskName(taskName);
        setTaskDescription(taskDescription);
    }

    // Setters
    public void setTaskName(final String taskName) {
        Validations.validateObjectNotNull(taskName);
        Validations.validateStringLengthIsInRange(taskName, 20);
        this.taskName = taskName;
    }

    public void setTaskDescription(final String taskDescription) {
        Validations.validateObjectNotNull(taskDescription);
        Validations.validateStringLengthIsInRange(taskDescription, 50);
        this.taskDescription = taskDescription;
    }

    // Getters
    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        return "Task Name: " + taskName + "\nTask Description: " + taskDescription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return (obj instanceof Task) &&
                ((Task) obj).getTaskName().equals(taskName) &&
                ((Task) obj).getTaskDescription().equals(taskDescription);
    }
}