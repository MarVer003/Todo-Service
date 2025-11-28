package com.challenge.todo.dao;

import com.challenge.todo.entity.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BaseDao implements PanacheRepositoryBase<Task, UUID> {

}