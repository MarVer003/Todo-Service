package com.challenge.todo;

import com.challenge.todo.control.TaskRepository;
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
    TaskRepository taskRepository;

    @GET
    public List<Task> getAllTasks() {
        return taskRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") Long id) {
        return taskRepository.findById(id);
    }

    @POST
    @Transactional
    public void createTask(Task task) {
        taskRepository.persist(task);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteTask(@PathParam("id") Long id) {
        taskRepository.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id);
        if (existingTask != null) {
            String title = updatedTask.getTitle() == null
                    ? existingTask.getTitle()
                    : updatedTask.getTitle();
            String description = updatedTask.getDescription() == null
                    ? existingTask.getDescription()
                    : updatedTask.getDescription();
            Boolean completion = updatedTask.isCompleted() == null
                    ? existingTask.isCompleted()
                    : updatedTask.isCompleted();

            existingTask.setTitle(title);
            existingTask.setDescription(description);
            existingTask.setCompleted(completion);
        }
    }
}