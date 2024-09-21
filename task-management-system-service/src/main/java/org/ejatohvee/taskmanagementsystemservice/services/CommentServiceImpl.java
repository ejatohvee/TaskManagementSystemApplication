package org.ejatohvee.taskmanagementsystemservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystemcore.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.Comment;
import org.ejatohvee.taskmanagementsystemcore.entities.Task;
import org.ejatohvee.taskmanagementsystemcore.mapper.CommentMapper;
import org.ejatohvee.taskmanagementsystemcore.repositories.CommentRepository;
import org.ejatohvee.taskmanagementsystemcore.repositories.TaskRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@ComponentScan(basePackages = "org.ejatohvee.taskmanagementsystemcore")
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    @Override
    public boolean deleteComment(UUID id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public void createComment(String body, String author, UUID taskId) {
        Task task = taskRepository.getTaskById(taskId);
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
    public CommentDTO findComment(UUID id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No comment for such id found"));
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public Iterable<CommentDTO> getAllComments() {
        Iterable<Comment> comments = commentRepository.findAll();
        return StreamSupport.stream(comments.spliterator(), false).map(commentMapper::commentToCommentDto).toList();
    }
}
