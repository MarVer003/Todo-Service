package com.challenge.todo.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "task")
public class Task{

    @Id
    private UUID id;

    private String title;
    private String description;
    private Boolean completed;

    public Task(){}

    public Task(String title, String description, Boolean completed) {
        this.id = UUID.randomUUID();
        this.title = title;
        this. description = description;
        this.completed = completed;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
