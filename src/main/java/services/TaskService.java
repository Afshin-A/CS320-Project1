package services;

import data.Task;

public final class TaskService extends Service<Task> {

    public void update(String id, String taskName, String taskDescription) {
        super.update(id, new Task(taskName, taskDescription));
    }

    @Override
    public void update(String id, Task task) {
        super.update(id, task);
    }
}