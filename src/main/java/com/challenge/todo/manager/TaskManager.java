package com.challenge.todo.manager;

import com.challenge.todo.repository.TaskRepository;
import com.challenge.todo.entity.Task;
import com.challenge.todo.validation.Validator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class TaskManager {

    @Inject
    TaskRepository repository;

    public Response createTask(Task task) {

        task = Validator.taskValidate(task);
        repository.persist(task);

        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    public List<Task> getAllTasks() {
        return repository.listAll();
    }

    public Response getTaskById(UUID id) {
        Task task = repository.findById(id);

        if(task == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(task).build();
    }

    public Response updateTask(@PathParam("id") UUID id, Task updatedTask) {
        Task existingTask = repository.findById(id);
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
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(existingTask).build();
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }

    public Response deleteTask(@PathParam("id") UUID id) {
        Task task = repository.findById(id);
        if(task != null) {
            repository.deleteById(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
