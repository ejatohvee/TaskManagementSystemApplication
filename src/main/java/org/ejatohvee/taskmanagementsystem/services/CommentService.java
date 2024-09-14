package org.ejatohvee.taskmanagementsystem.services;

import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.entities.Task;

import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    void createComment(String body, String author, Task task);

    void updateComment(UUID id, String body);
    Optional<Comment> findComment(UUID id);
    boolean deleteComment(UUID id);
    Iterable<Comment> getAllComments();
}
