package org.ejatohvee.taskmanagementsystemservice.services;

import org.ejatohvee.taskmanagementsystemcore.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskStatus;

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
