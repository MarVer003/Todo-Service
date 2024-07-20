package com.challenge.todo.resource;

import com.challenge.todo.entity.Task;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;
import java.util.UUID;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TaskResource {

    @POST
    @Operation(summary = "Create task",
            description = "Creates a task with given values. If TITLE isn't given, an error will be thrown." +
                    " DESCRIPTION default value is null. COMPLETED default value is false."
    )
    Response createTask(Task task);

    @GET
    @Operation(summary = "Get all tasks")
    List<Task> getAllTasks();

    @GET
    @Path("/{id}")
    @Operation(summary = "Get task by id")
    Response getTaskById(@PathParam("id") UUID id);

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update task",
            description = "Updates specified values only. If none are provided, nothing changes."
    )
    Response updateTask(@PathParam("id") UUID id, Task updatedTask);

    @DELETE
    @Operation(summary = "Delete all tasks")
    void deleteAllTasks();

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete task by id")
    Response deleteTask(@PathParam("id") UUID id);
}