package com.challenge.todo.control;

import com.challenge.todo.entity.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepositoryBase<Task, Integer> {

    public List<Task> listAllTasks() {
        return findAll().list();
    }

    public Task findTaskById(Long id) {
        return find("id", id).firstResult();
    }

    public void persistTask(Task task) {
        task.persist();
    }

    public void deleteTaskById(Long id) {
        findTaskById(id).delete();
    }
}