package com.challenge.todo.manager;

import com.challenge.todo.repository.TaskRepository;

import static org.mockito.Mockito.mock;

public class TaskManagerTestDouble extends TaskManager {

    public TaskManagerTestDouble() {
        repository = mock(TaskRepository.class);
    }
}
