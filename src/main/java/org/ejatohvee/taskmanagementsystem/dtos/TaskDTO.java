package org.ejatohvee.taskmanagementsystem.dtos;

import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public record TaskDTO(UUID id, String title, String description, TaskStatus status, TaskPriority priority, String author, String performer, List<CommentDTO> comments) {}
