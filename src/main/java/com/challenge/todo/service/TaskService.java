package com.challenge.todo.service;

import com.challenge.todo.dao.BaseDao;
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
public class TaskService {

    @Inject
    BaseDao baseDao;

    public Response createTask(Task task) {

        task = Validator.taskValidate(task);
        baseDao.persist(task);

        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    public List<Task> getAllTasks() {
        return baseDao.listAll();
    }

    public Response getTaskById(UUID id) {
        Task task = baseDao.findById(id);

        if(task == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(task).build();
    }

    public Response updateTask(@PathParam("id") UUID id, Task updatedTask) {
        Task existingTask = baseDao.findById(id);
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
        baseDao.deleteAll();
    }

    public Response deleteTask(@PathParam("id") UUID id) {
        Task task = baseDao.findById(id);
        if(task != null) {
            baseDao.deleteById(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
