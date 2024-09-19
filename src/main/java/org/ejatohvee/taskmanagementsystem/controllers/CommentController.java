package org.ejatohvee.taskmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystem.payloads.CommentPayload;
import org.ejatohvee.taskmanagementsystem.services.CommentService;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
    private final TaskService taskService;
    private final CommentService commentService;
    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") UUID commentId) {
        return ResponseEntity.ok(commentService.findComment(commentId));
    }

    @PostMapping("{taskId}")
    public ResponseEntity<Void> addComment(@PathVariable("taskId") UUID taskId, @RequestBody CommentPayload payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        commentService.createComment(payload.body(), authentication.getName(), taskService.findTask(taskId));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") UUID commentId, @RequestBody CommentPayload payload) {
        commentService.updateComment(commentId, payload.body());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") UUID commentId) {
        if (commentService.deleteComment(commentId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
