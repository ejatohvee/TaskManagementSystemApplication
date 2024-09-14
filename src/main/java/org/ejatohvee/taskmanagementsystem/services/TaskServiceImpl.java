package org.ejatohvee.taskmanagementsystem.services;

import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByUsername(String username) {
        return taskRepository.getTasksByAuthor(username);
    }

    @Override
    public Optional<Task> findTask(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Task createTask(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer) {
        return taskRepository.save(new Task(title, description, status, priority, author, performer));
    }

    @Override
    @Transactional
    public void updateTask(UUID id, String title, String description, TaskStatus status, TaskPriority priority, String performer) {
        taskRepository.findById(id).ifPresentOrElse(task -> {
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setPriority(priority);
            task.setPerformer(performer);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public boolean deleteTask(UUID id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    public String getTaskAuthorUsername(UUID id) {
        return taskRepository.findById(id).map(
                Task::getAuthor).orElse(null);
    }


}
