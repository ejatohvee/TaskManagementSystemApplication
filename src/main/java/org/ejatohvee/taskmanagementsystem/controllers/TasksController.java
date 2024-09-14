package org.ejatohvee.taskmanagementsystem.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.payloads.NewTaskPayload;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("catalogue/tasks")
public class TasksController {
    private final TaskService taskService;

    @GetMapping("list")
    public ResponseEntity<?> getTasksList() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("my_tasks")
    public ResponseEntity<?> getUsersTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(taskService.getTasksByUsername(username));
    }

    @PostMapping("create")
    public ResponseEntity<?> createTask(@Valid @RequestBody NewTaskPayload payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return ResponseEntity.ok(taskService.createTask(payload.title(), payload.description(), payload.status(), payload.priority(), currentUsername, payload.performer()));
    }
}