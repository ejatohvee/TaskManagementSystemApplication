package org.ejatohvee.taskmanagementsystem.dtos;

import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;

import java.util.List;

public record TaskDTO(String title, String description, TaskStatus status, TaskPriority priority, String author, String performer, List<CommentDTO> comments) {}
