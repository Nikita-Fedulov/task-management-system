package ru.dev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dev.model.Role;
import ru.dev.model.Task;
import ru.dev.repository.TaskRepository;
import ru.dev.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {


    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
    }

    public Long createTask(String title, String details) {
        Task task = Task.builder()
                .title(title)
                .description(details)
                .build();
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    public void updateTask(Long taskId, String newTitle, String newDescription) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        existingTask.setTitle(newTitle);
        existingTask.setDescription(newDescription);

        taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}

