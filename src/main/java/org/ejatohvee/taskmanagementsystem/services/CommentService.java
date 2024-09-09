package org.ejatohvee.taskmanagementsystem.services;

import jakarta.persistence.Table;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.entities.Task;

import java.util.List;

public interface CommentService {
    Comment createComment(String body, String author, Task task);

    Iterable<Comment> getAllComments();
}
