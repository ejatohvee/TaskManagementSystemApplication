package org.ejatohvee.taskmanagementsystem.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.ejatohvee.taskmanagementsystem.TaskPriority;
import org.ejatohvee.taskmanagementsystem.TaskStatus;

public record NewTaskPayload(
        @NotNull(message = "{catalogue.tasks.create.errors.title.not_null}")
        @Size(min = 3, max = 50, message = "{catalogue.tasks.create.errors.title.size}")
        String title,
        @Size(max = 500, message = "{catalogue.tasks.create.errors.description.size}")
        String description,
        @NotNull(message = "{catalogue.tasks.create.errors.status.not_null}")
        TaskStatus status,
        @NotNull(message = "{catalogue.tasks.create.errors.priority.not_null}")
        TaskPriority priority,
        @NotNull(message = "{catalogue.tasks.create.errors.performer.not_null}")
        @Size(min = 1, max = 20, message = "{catalogue.tasks.create.errors.performer.size}")
        String performer) {
}
