package org.ejatohvee.taskmanagementsystem.services;

import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;
import org.ejatohvee.taskmanagementsystem.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    Iterable<Task> getAllTasks();

    Optional<Task> findTask(UUID id);
    List<Task> getTasksByUsername(String username);

    Task createTask(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer);

    void updateTask(UUID id, String title, String description, TaskStatus status, TaskPriority priority, String performer);

    boolean deleteTask(UUID id);

    String getTaskAuthorUsername(UUID id);
}
