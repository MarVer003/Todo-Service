package com.challenge.todo.validation;

import com.challenge.todo.entity.Task;

public class Validator {

    public static Task taskValidate(Task task) {

        Task tmpTask = new Task(task.getTitle(), task.getDescription(), task.isCompleted());

        if(tmpTask.getTitle() == null || tmpTask.getTitle().isBlank()) {
            throw new RuntimeException("Title cannot be blank or null.");
        }

        if(tmpTask.getDescription() == null)
            tmpTask.setDescription("");

        if(tmpTask.isCompleted() == null)
            tmpTask.setCompleted(false);

        return tmpTask;
    }
}
