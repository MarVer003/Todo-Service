package com.challenge.todo.manager;

import com.challenge.todo.repository.TaskRepository;
import com.challenge.todo.entity.Task;
import com.challenge.todo.resource.TaskResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@Transactional
public class TaskManager {

    @Inject
    TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.listAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id);
    }

    public Response createTask(Task task) {
        if(task.getTitle() == null || task.getTitle().isBlank()) {
            System.err.println("Title cannot be blank or null.");
            throw new RuntimeException("Title cannot be blank or null.");
        }
        if(task.isCompleted() == null)
            task.setCompleted(false);

        repository.persist(task);


        return Response.created(buildUri(task)).entity(task).build();
    }

    public Response deleteTask(@PathParam("id") Long id) {
        repository.deleteById(id);

        return Response.created(buildUri(repository.findById(id))).build();
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }

    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
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

        return Response.created(buildUri(updatedTask)).build();
    }

    private URI buildUri(Task task) {
        return UriBuilder.fromResource(TaskResource.class)
                .path(Long.toString((task.getId())))
                .build();
    }
}
