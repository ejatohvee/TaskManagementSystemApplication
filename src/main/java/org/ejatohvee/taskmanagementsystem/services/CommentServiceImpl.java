package org.ejatohvee.taskmanagementsystem.services;

import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public boolean deleteComment(UUID id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    public void createComment(String body, String author, Task task) {
        commentRepository.save(new Comment(body, author, task));
    }

    @Override
    @Transactional
    public void updateComment(UUID id, String body) {
        commentRepository.findById(id).ifPresentOrElse(comment ->
                comment.setBody(body), () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public Optional<Comment> findComment(UUID id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
