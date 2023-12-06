package ru.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.model.Task;
import ru.dev.service.TaskService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<Long> createTask(@RequestBody @Valid Task task) {
        long taskId = taskService.createTask(task.getTitle(), task.getDescription());
        return ResponseEntity.ok(taskId);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable long taskId, @RequestBody @Valid Task task) {
        taskService.updateTask(taskId, task.getTitle(), task.getDescription());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}

