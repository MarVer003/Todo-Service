package com.challenge.todo.repository;

import com.challenge.todo.entity.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TaskRepository implements PanacheRepositoryBase<Task, UUID> {

}