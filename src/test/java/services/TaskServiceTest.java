package services;

import data.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    TaskService taskService;
    Task task1;
    Task task2;

    @BeforeEach
    void init() {
        taskService = new TaskService();
        task1 = new Task("CS Homework", "Complete CS320 Module 4 assignments on time!");
        task2 = new Task("Laundry", "Do laundry tomorrow.");
    }

    @Test
    void taskIDNullTest() {
        assertThrows(IllegalArgumentException.class, () -> taskService.add("", task1), "taskID cannot be empty. Should throw IllegalArgumentException.");
    }

    @Test
    void taskIDTooLongTest() {
        assertThrows(IllegalArgumentException.class, () -> taskService.add("01234567890", task1), "taskID length should be 10 at most. Should throw IllegalArgumentException.");
    }

    @Test
    void taskIDHasRightLength() {
        assertDoesNotThrow(() -> taskService.add("1234567890", task1), "taskID is 10. Should not throw any exceptions.");
    }

    @Test
    void taskServiceAddTest() {
        taskService.add("123", task1);
        assertTrue(taskService.database.containsKey("123"), "add should add task to underlying ADT (hashMap)");
        assertEquals(task1, taskService.database.get("123"));
    }

    @Test
    void taskMapImmutableTest() {
        taskService.add("123", task1);
        taskService.add("123", task2);
        assertNotEquals(task2, taskService.database.get("123"), "add() cannot override an existing id in database");
    }

    @Test
    void taskServiceRemoveTest() {
        taskService.add("123", task1);
        taskService.remove("123");
        assertFalse(taskService.database.containsKey("123"), "ADT (hashMap) should no longer contain a taskID");
    }

    @Test
    void taskServiceRemoveWithNonExistentID() {
        // if taskID does not exist in database, taskService.removeTask() should do nothing
    }

    @Test
    void taskServiceOverriddenUpdateTest() {
        taskService.add("123", task1);
        taskService.update("123", task2);
        assertEquals(task2, taskService.database.get("123"));
    }

    @Test
    void taskServiceUpdateTest() {
        taskService.add("123", task1);
        taskService.update("123", task2.getTaskName(), task2.getTaskDescription());
        assertEquals(task2, taskService.database.get("123"));
    }
}