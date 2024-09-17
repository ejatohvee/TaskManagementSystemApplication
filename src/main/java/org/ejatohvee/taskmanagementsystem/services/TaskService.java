package org.ejatohvee.taskmanagementsystem.services;

import org.ejatohvee.taskmanagementsystem.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Iterable<TaskDTO> getAllTasks();

    TaskDTO findTask(UUID id);

    List<TaskDTO> getTasksByUsername(String username);

    TaskDTO createTask(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer);

    void updateTask(UUID id, String title, String description, TaskStatus status, TaskPriority priority, String performer);

    boolean deleteTask(UUID id);

    String getTaskAuthorUsername(UUID id);
}
