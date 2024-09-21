package org.ejatohvee.taskmanagementsystemservice.services;



import org.ejatohvee.taskmanagementsystemcore.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystemcore.dtos.TaskDTO;

import java.util.UUID;

public interface CommentService {
    void createComment(String body, String author, UUID taskId);

    void updateComment(UUID id, String body);

    CommentDTO findComment(UUID id);

    boolean deleteComment(UUID id);

    Iterable<CommentDTO> getAllComments();
}
