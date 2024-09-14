package org.ejatohvee.taskmanagementsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.payloads.UpdateTaskPayload;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("catalogue/tasks/{taskId}")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTask(@PathVariable("taskId") UUID taskId) {
        Optional<Task> task = taskService.findTask(taskId);
        if (task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        } else return ResponseEntity.ok(task);
    }

    @PutMapping
    @PreAuthorize("authentication.name == @taskServiceImpl.getTaskAuthorUsername(#taskId)")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") UUID taskId, @Valid @RequestBody UpdateTaskPayload payload) {
        taskService.updateTask(taskId, payload.title(), payload.description(), payload.status(), payload.priority(), payload.performer());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @PreAuthorize("authentication.name == @taskServiceImpl.getTaskAuthorUsername(#taskId)")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") UUID taskId) {
        if (taskService.deleteTask(taskId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}