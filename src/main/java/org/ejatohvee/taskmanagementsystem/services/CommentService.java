package org.ejatohvee.taskmanagementsystem.services;

import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;


import java.util.UUID;

public interface CommentService {
    void createComment(String body, String author, UUID id);

    void updateComment(UUID id, String body);

    CommentDTO findComment(UUID id);

    boolean deleteComment(UUID id);

    Iterable<CommentDTO> getAllComments();
}
