package org.ejatohvee.taskmanagementsystemcore.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskPriority;
import org.ejatohvee.taskmanagementsystemcore.entities.enums.TaskStatus;

public record UpdateTaskPayload(
        @NotNull(message = "{catalogue.tasks.update.errors.title.not_null}")
        @Size(min = 3, max = 50, message = "{catalogue.tasks.create.errors.title.size}")
        String title,
        @Size(max = 500, message = "{catalogue.tasks.update.errors.description.size}")
        String description,
        @NotNull(message = "{catalogue.tasks.update.errors.status.not_null}")
        TaskStatus status,
        @NotNull(message = "{catalogue.tasks.update.errors.status.not_null}")
        TaskPriority priority,
        @NotNull(message = "{catalogue.tasks.update.errors.status.not_null}")
        @Size(min = 1, max = 20, message = "{catalogue.tasks.update.errors.performer.size}")
        String performer) {
}
