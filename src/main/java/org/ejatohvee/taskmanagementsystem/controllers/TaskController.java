package org.ejatohvee.taskmanagementsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.payloads.UpdateTaskPayload;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue/tasks/{taskId}")
public class TaskController {
    private final TaskService taskService;

    @ModelAttribute("task")
    public Task task(@PathVariable("taskId") UUID taskId) {
        return taskService.findTask(taskId).orElseThrow();
    }

    @GetMapping("edit")
    public String updateTask() {
        return "catalogue/tasks/edit";
    }

    @PostMapping("edit")
    public String updateTask(@ModelAttribute(name = "task", binding = false) Task task, @Valid UpdateTaskPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "catalogue/tasks/edit";
        } else {
            taskService.updateTask(task.getId(), payload.title(), payload.description(), payload.status(), payload.priority(), payload.performer());
            return "redirect:/catalogue/tasks/my_tasks";
        }
    }

    @PostMapping("delete")
    public String deleteTask(@ModelAttribute("task") Task task) {
        taskService.deleteTask(task.getId());
        return "redirect:/catalogue/tasks/my_tasks";
    }
}

// TODO correct usernames in methods