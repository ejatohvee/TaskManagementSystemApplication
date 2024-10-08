package org.ejatohvee.taskmanagementsystemweb.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystemcore.dtos.TaskDTO;
import org.ejatohvee.taskmanagementsystemcore.payloads.NewTaskPayload;
import org.ejatohvee.taskmanagementsystemcore.payloads.UpdateTaskPayload;
import org.ejatohvee.taskmanagementsystemservice.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<Iterable<TaskDTO>> getTasksList() {
        Iterable<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("my_tasks")
    public ResponseEntity<List<TaskDTO>> getUsersTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TaskDTO> tasks = taskService.getTasksByUsername(authentication.getName());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("create")
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody NewTaskPayload payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaskDTO task = taskService.createTask(payload.title(), payload.description(), payload.status(), payload.priority(), authentication.getName(), payload.performer());
        return ResponseEntity.ok(task);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("taskId") UUID taskId) {
        TaskDTO task = taskService.findTask(taskId);
        return ResponseEntity.ok(task);
    }

    @PutMapping("{taskId}")
    @PreAuthorize("authentication.name == @taskServiceImpl.getTaskAuthorUsername(#taskId)")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") UUID taskId, @Valid @RequestBody UpdateTaskPayload payload) {
        taskService.updateTask(taskId, payload.title(), payload.description(), payload.status(), payload.priority(), payload.performer());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{taskId}")
    @PreAuthorize("authentication.name == @taskServiceImpl.getTaskAuthorUsername(#taskId)")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") UUID taskId) {
        if (taskService.deleteTask(taskId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}