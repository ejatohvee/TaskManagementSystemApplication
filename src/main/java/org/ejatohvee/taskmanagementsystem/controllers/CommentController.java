package org.ejatohvee.taskmanagementsystem.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.payloads.CommentPayload;
import org.ejatohvee.taskmanagementsystem.services.CommentService;
import org.ejatohvee.taskmanagementsystem.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping("{commentId}")
    public ResponseEntity<?> getComment(@PathVariable("commentId") UUID commentId) {
        Optional<Comment> comment = commentService.findComment(commentId);
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("{taskId}")
    public ResponseEntity<Void> addComment(@PathVariable("taskId") UUID taskId, @RequestBody CommentPayload payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author = authentication.getName();
        taskService.findTask(taskId).ifPresent(task ->  commentService.createComment(payload.body(), author, task));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") UUID commentId, @RequestBody CommentPayload payload) {
        commentService.updateComment(commentId, payload.body());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") UUID commentId) {
        if (commentService.deleteComment(commentId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
