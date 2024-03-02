package com.challenge.todo.resource;

import com.challenge.todo.entity.Task;
import com.challenge.todo.manager.TaskManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;
import java.util.UUID;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskManager manager;

    @POST
    @Operation(summary = "Create task",
            description = "Creates a task with given values. If TITLE isn't given, an error will be thrown." +
                    " DESCRIPTION default value is null. COMPLETED default value is false."
    )
    public Response createTask(Task task) {
        return manager.createTask(task);
    }

    @GET
    @Operation(summary = "Get all tasks")
    public List<Task> getAllTasks() {
        return manager.getAllTasks();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get task by id")
    public Response getTaskById(@PathParam("id") UUID id) {
        return manager.getTaskById(id);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update task",
            description = "Updates specified values only. If none are provided, nothing changes."
    )
    public Response updateTask(@PathParam("id") UUID id, Task updatedTask) {
        return manager.updateTask(id, updatedTask);
    }

    @DELETE
    @Operation(summary = "Delete all tasks")
    public void deleteAllTasks() {
        manager.deleteAllTasks();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete task by id")
    public Response deleteTask(@PathParam("id") UUID id) {
        return manager.deleteTask(id);
    }
}