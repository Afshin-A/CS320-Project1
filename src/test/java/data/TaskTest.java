package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    String taskName;
    String taskDescription;
    Task task;
    @BeforeEach
    void init() {
        task = new Task("N/A", "N/A");
        taskName = "Oops! I accidentally entered task description for task name";
        taskDescription = "Midway upon the journey of our life. I found myself within a forest dark, For the straightforward pathway had been lost.";
    }

    @Test
    void taskNameNullTest() {
        assertThrows(IllegalArgumentException.class, () -> task.setTaskName(null),
                "taskName cannot be null");
    }

    @Test
    void taskNameEmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> task.setTaskName(""),
                "taskName cannot be empty");
    }

    @Test
    void taskNameTooLongTest() {
        String finalTaskName = taskName.substring(0, 21);
        assertEquals(21, finalTaskName.length());
        assertThrows(IllegalArgumentException.class, ()-> task.setTaskName(finalTaskName),
                "taskName cannot be longer than 20 chars");
    }

    @Test
    @DisplayName("taskName length at threshold")
    void taskNameHasMaxLengthTest() {
        String finalTaskName = taskName.substring(0, 20);
        assertEquals(20, finalTaskName.length());
        assertDoesNotThrow(()->task.setTaskName(finalTaskName), "taskName is 20. There should be no errors");
    }


    @Test
    void taskDescriptionNullTest() {
        assertThrows(IllegalArgumentException.class, ()->task.setTaskDescription(null), "taskDescription cannot be null");
    }

    @Test
    void taskDescriptionEmptyTest() {
        assertThrows(IllegalArgumentException.class, ()->task.setTaskDescription(""), "taskDescription cannot be empty");
    }

    @Test
    void taskDescriptionTooLongTest() {
        String finalTaskDescription = taskDescription.substring(0, 51);
        assertEquals(51, finalTaskDescription.length());
        assertThrows(IllegalArgumentException.class, ()->task.setTaskDescription(finalTaskDescription),
                "taskDescription is longer than 50. IllegalArgumentException should be thrown.");
    }

    @Test
    void taskDescriptionHasMaxLengthTest() {
        String finalTaskDescription = taskDescription.substring(0, 50);
        assertEquals(50, finalTaskDescription.length());
        assertDoesNotThrow(()->task.setTaskDescription(finalTaskDescription), "taskDescription has the maximum " +
                "allowed length. No exceptions should be thrown.");
    }

    @Test
    void taskNotEqualsNullTest() {
        assertFalse(()->task.equals(null));
    }

    @Test
    void taskEqualsItselfTest() {
        assertTrue(()-> task.equals(task));
    }

    @Test
    void taskNotEqualsDifferentObjectTypeTest() {
        assertFalse(()->task.equals("N/A"));
    }

    @Test
    void taskEqualsTest() {
        assertTrue(()->task.equals(new Task("N/A", "N/A")),
                "BeforeEach() method instantiates task with \"N/A\".");
    }

    @Test
    void tasksNotEqualTest() {
        String finalTaskName = taskName.substring(0, 20);
        String finalTaskDescription = taskDescription.substring(0, 50);
        assertFalse(()->task.equals(new Task(finalTaskName, finalTaskDescription)));
    }
}