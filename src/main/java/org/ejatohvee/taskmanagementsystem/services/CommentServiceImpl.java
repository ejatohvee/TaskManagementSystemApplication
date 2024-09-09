package org.ejatohvee.taskmanagementsystem.services;

import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public Comment createComment(String body, String author, Task task) {
        return commentRepository.save(new Comment(body, author, task));
    }

    @Override
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
