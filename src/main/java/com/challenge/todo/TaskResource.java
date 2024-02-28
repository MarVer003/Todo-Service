package com.challenge.todo;

import com.challenge.todo.control.TaskControl;
import com.challenge.todo.entity.Task;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskControl taskControl;

    @GET
    public List<Task> getAllTasks() {
        return taskControl.listAllTasks();
    }

    @GET
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") Long id) {
        return taskControl.findTaskById(id);
    }

    @POST
    @Transactional
    public void createTask(Task task) {
        taskControl.persistTask(task);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteTask(@PathParam("id") Long id) {
        taskControl.deleteTaskById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task existingTask = taskControl.findTaskById(id);
        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
        }
    }
}