package org.ejatohvee.taskmanagementsystem.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.payloads.NewTaskPayload;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("catalogue/tasks")
public class TasksController {
    private final TaskService taskService;

    @GetMapping("list")
    public String getTasksList(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "catalogue/tasks/list";
    }

    @GetMapping("my_tasks")
    public String getUsersTasks(Model model) {
//        String currentUsername = authentication.getName();
        String username = "user";
        List<Task> tasks = taskService.getTasksByUsername(username);
        model.addAttribute("tasks", tasks);
        return "catalogue/tasks/my_tasks";
    }

    @GetMapping("create")
    public String createTask() {
        return "catalogue/tasks/create";
    }

    @PostMapping("create")
    public String createTask(@Valid NewTaskPayload payload, BindingResult bindingResult, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserEmail = authentication.getName();
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "catalogue/tasks/create";
        } else {
            taskService.createTask(payload.title(), payload.description(), payload.status(), payload.priority(), "user", payload.performer());
            return "redirect:/catalogue/tasks/list";
        }
    }
}

// TODO Payload validation
// TODO correct usernames in methods