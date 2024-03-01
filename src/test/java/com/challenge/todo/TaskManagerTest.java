package com.challenge.todo;

import com.challenge.todo.entity.Task;
import com.challenge.todo.manager.TaskManagerTestDouble;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TaskManagerTest {

    private TaskManagerTestDouble taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManagerTestDouble();
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        Response createResponse = taskManager.createTask(task);
        assertEquals(201, createResponse.getStatus(), "Create Task should return 201");


    }
    @Test
    void testReadTask() {
        Task task = new Task();
        task.setId(2L);
        task.setTitle("Test Task 2");
        taskManager.createTask(task);


        Task retrievedTask = taskManager.getTaskById(2L);
        assertEquals("Test Task 2", retrievedTask.getTitle(), "Get Task should return a task with Test Task 2 title");
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setId(3L);
        task.setTitle("Test Task 3");
        taskManager.createTask(task);

        Task task2 = new Task();
        task2.setId(3L);
        task2.setTitle("Test Task 3 UPDATED");
        Response updateResponse = taskManager.updateTask(2L, task2);
        assertEquals(201, updateResponse.getStatus(), "Update Task should return 201");
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setId(4L);
        task.setTitle("Test Task");
        taskManager.createTask(task);

        System.out.println(task.getId());
        Response deleteResponse = taskManager.deleteTask(task.getId());

        assertEquals(200, deleteResponse.getStatus(), "Delete Task should return 200");
    }
}