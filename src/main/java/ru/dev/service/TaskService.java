package ru.dev.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.dev.DTO.TaskDTO;
import ru.dev.model.Priority;
import ru.dev.model.Task;
import ru.dev.model.TaskStatus;
import ru.dev.model.User;
import ru.dev.repository.TaskRepository;
import ru.dev.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
    }

    public Long createTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }
    @Transactional
    public Task updateTask(Long taskId, TaskDTO updatedTaskDTO) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        if (existingTask != null) {
            String updatedTitle = updatedTaskDTO.getTitle();
            if (updatedTitle != null && !updatedTitle.isBlank()) {
                existingTask.setTitle(updatedTitle);
            }

            String updatedDescription = updatedTaskDTO.getDescription();
            if (updatedDescription != null) {
                existingTask.setDescription(updatedDescription);
            }

            TaskStatus updatedStatus = updatedTaskDTO.getStatus();
            if (updatedStatus != null) {
                existingTask.setStatus(updatedStatus);
            }

            Priority updatedPriority = updatedTaskDTO.getPriority();
            if (updatedPriority != null) {
                existingTask.setPriority(updatedPriority);
            }

            return taskRepository.save(existingTask);
        }
        return null;
    }

    public Task addComment(Long taskId, String commentText) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        task.getComments().add(commentText);
        return taskRepository.save(task);
    }


    public Task updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public Task assignTaskExecutor(Long taskId, Long executorId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        User executor = userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + executorId));

        if (task.getExecutor() != null) {
            throw new IllegalArgumentException("Task already has an executor.");
        }

        task.setExecutor(executor);
        return taskRepository.save(task);
    }


    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }


}

