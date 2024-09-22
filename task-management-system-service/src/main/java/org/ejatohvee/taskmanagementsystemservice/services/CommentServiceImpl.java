package org.ejatohvee.taskmanagementsystem.services;

import lombok.AllArgsConstructor;
import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.entities.Task;
import org.ejatohvee.taskmanagementsystem.mapper.CommentMapper;
import org.ejatohvee.taskmanagementsystem.mapper.TaskMapper;
import org.ejatohvee.taskmanagementsystem.repositories.CommentRepository;
import org.ejatohvee.taskmanagementsystem.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
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
    public void createComment(String body, String author, UUID id) {
        Task task = taskRepository.getTaskById(id);
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
