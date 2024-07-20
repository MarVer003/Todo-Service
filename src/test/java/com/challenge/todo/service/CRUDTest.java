package com.challenge.todo.service;

import com.challenge.todo.entity.Task;
import com.challenge.todo.validation.Validator;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CRUDTest {

    @Inject
    TaskService taskService;

    @Test
    public void crudTest1() {
        Task et = Validator.taskValidate(new Task("Test-Title", "Test-Description", false));
        Task ut = new Task("Test-Title2", "Test-Description2", true);
        doTest(et, ut);
    }

    @Test
    public void crudTest2() {
        Task et = Validator.taskValidate(new Task("Test-Title10", null, false));
        Task ut = (new Task(null, "null", true));
        doTest(et, ut);
    }

    @Test
    public void crudTest3() {
        Task et = Validator.taskValidate(new Task("Test-Title100", null, null));
        Task ut = (new Task(null, null, null));
        doTest(et, ut);
    }

    private void doTest(Task expectedTask, Task updatedTask) {
        UUID uuid = create(expectedTask);
        read(uuid, expectedTask);
        update(uuid, updatedTask);
        delete(uuid);
    }

    private UUID create(Task t) {
        Task task;
        try (Response response = taskService.createTask(t)) {

            task = response.readEntity(Task.class);

            assertEquals(201, response.getStatus());
        }
        assertEquals(t.getTitle(), task.getTitle());
        assertEquals(t.getDescription(), task.getDescription());
        assertEquals(t.isCompleted(), task.isCompleted());

        return task.getId();
    }

    private void read(UUID uuid, Task expectedTask) {

        Response response = taskService.getTaskById(uuid);
        Task task = response.readEntity(Task.class);

        assertEquals(200, response.getStatus());
        assertEquals(expectedTask.getTitle(), task.getTitle());
        assertEquals(expectedTask.getDescription(), task.getDescription());
        assertEquals(expectedTask.isCompleted(), task.isCompleted());
    }

    private void update(UUID uuid, Task updatedTask) {

        Task task;
        try (Response response = taskService.updateTask(uuid, updatedTask)) {
            task = response.readEntity(Task.class);

            assertEquals(200, response.getStatus());
        }
        assertEquals(updatedTask.getTitle() == null
                ? task.getTitle()
                : updatedTask.getTitle(), task.getTitle());
        assertEquals(updatedTask.getDescription() == null
                ? ""
                : updatedTask.getDescription(), task.getDescription());
        assertEquals(updatedTask.isCompleted() != null
                && updatedTask.isCompleted(), task.isCompleted());
    }

    private void delete(UUID uuid) {

        try (Response response = taskService.deleteTask(uuid)) {

            assertEquals(204, response.getStatus());
        }
    }
}
