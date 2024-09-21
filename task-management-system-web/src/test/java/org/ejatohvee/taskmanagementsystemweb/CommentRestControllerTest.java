package org.ejatohvee.taskmanagementsystemweb;

import org.ejatohvee.taskmanagementsystemcore.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.Comment;
import org.ejatohvee.taskmanagementsystemservice.services.CommentService;
import org.ejatohvee.taskmanagementsystemweb.controllers.CommentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentRestControllerTest {

    @Mock
    CommentService commentService;

    @InjectMocks
    CommentController commentController;

    @Test
    void handleGetComment_ReturnsValidResponseEntity() {
        UUID id = UUID.randomUUID();
        Comment mockComment = new Comment("Sample body", "author", null);
        mockComment.setId(id);
        CommentDTO commentDTO = new CommentDTO(id, mockComment.getBody(), mockComment.getAuthor(), mockComment.getTime());

        when(commentService.findComment(mockComment.getId())).thenReturn(commentDTO);

        var responseEntity = commentController.getComment(mockComment.getId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertEquals(commentDTO, responseEntity.getBody());
    }
}
