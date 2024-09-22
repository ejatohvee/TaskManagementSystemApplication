package org.ejatohvee.taskmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystem.payloads.CommentPayload;
import org.ejatohvee.taskmanagementsystem.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping("{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("commentId") UUID commentId) {
        return ResponseEntity.ok(commentService.findComment(commentId));
    }

    @PostMapping("{taskId}")
    public ResponseEntity<Void> addComment(@PathVariable("taskId") UUID taskId, @RequestBody CommentPayload payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        commentService.createComment(payload.body(), authentication.getName(), taskId);
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
