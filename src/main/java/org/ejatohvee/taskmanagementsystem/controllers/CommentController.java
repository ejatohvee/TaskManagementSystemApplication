package org.ejatohvee.taskmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.payloads.CommentPayload;
import org.ejatohvee.taskmanagementsystem.services.CommentService;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("comment/{taskId}")
@RequiredArgsConstructor
public class CommentController {
    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping()
    public String addComment(@PathVariable("taskId") UUID taskId, Model model) {
        model.addAttribute("taskId", taskId);
        return "comment/comment_form";
    }

    @PostMapping()
    public String addComment(@PathVariable("taskId") UUID taskId, CommentPayload payload) {
        taskService.findTask(taskId).ifPresent(task ->  commentService.createComment(payload.body(), "author", task));

        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserEmail = authentication.getName();

        return "redirect:/catalogue/tasks/list";
    }


}
