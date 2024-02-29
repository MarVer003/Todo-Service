package com.challenge.todo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task{

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Boolean completed;

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
