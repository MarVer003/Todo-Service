package com.challenge.todo.resource;

import com.challenge.todo.entity.Task;
import com.challenge.todo.manager.TaskManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskManager manager;

    @GET
    public List<Task> getAllTasks() {
        return manager.getAllTasks();
    }

    @GET
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") Long id) {
        return manager.getTaskById(id);
    }

    @POST
    public void createTask(Task task) {
            manager.createTask(task);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTask(@PathParam("id") Long id) {
        manager.deleteTask(id);
    }

    @DELETE
    public void deleteAllTasks() {
        manager.deleteAllTasks();
    }

    @PUT
    @Path("/{id}")
    public void updateTask(@PathParam("id") Long id, Task updatedTask) {
        manager.updateTask(id, updatedTask);
    }
}