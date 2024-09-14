package org.ejatohvee.taskmanagementsystem.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystem.entities.enums.TaskStatus;

public record NewTaskPayload(
        @NotBlank(message = "{catalogue.tasks.create.errors.title.not_blank}")
        @Size(max = 50, message = "{catalogue.tasks.create.errors.title.size}")
        String title,
        @Size(max = 500, message = "{catalogue.tasks.create.errors.description.size}")
        String description,
        @NotNull(message = "{catalogue.tasks.create.errors.status.not_null}")
        TaskStatus status,
        @NotNull(message = "{catalogue.tasks.create.errors.priority.not_null}")
        TaskPriority priority,
        @NotBlank(message = "{catalogue.tasks.create.errors.performer.not_blank}")
        @Size(max = 20, message = "{catalogue.tasks.create.errors.performer.size}")
        String performer) {
}
