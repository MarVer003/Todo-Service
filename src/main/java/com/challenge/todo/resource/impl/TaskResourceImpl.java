package com.challenge.todo.resource.impl;

import com.challenge.todo.entity.Task;
import com.challenge.todo.resource.TaskResource;
import com.challenge.todo.service.TaskService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

public class TaskResourceImpl implements TaskResource {

    @Inject
    TaskService taskService;

    @Override
    public Response createTask(Task task) {
        return taskService.createTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Override
    public Response getTaskById(UUID id) {
        return taskService.getTaskById(id);
    }

    @Override
    public Response updateTask(UUID id, Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @Override
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

    @Override
    public Response deleteTask(UUID id) {
        return taskService.deleteTask(id);
    }
}