package org.ejatohvee.taskmanagementsystem;

import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.ejatohvee.taskmanagementsystem.mapper.CommentMapper;
import org.ejatohvee.taskmanagementsystem.repositories.CommentRepository;
import org.ejatohvee.taskmanagementsystem.services.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Test
    void handleFindComment_ReturnsComment() {
        UUID id = UUID.randomUUID();
        Comment mockComment = new Comment("Sample body", "author", null);
        mockComment.setId(id);

        when(commentRepository.findById(id)).thenReturn(Optional.of(mockComment));

        CommentDTO commentDTO = new CommentDTO(mockComment.getBody(), mockComment.getAuthor(), "22:10");
        when(commentMapper.commentToCommentDto(mockComment)).thenReturn(commentDTO);

        CommentDTO result = commentService.findComment(id);

        assertEquals("Sample body", result.body());

        verify(commentRepository, times(1)).findById(id);
        verify(commentMapper, times(1)).commentToCommentDto(mockComment);
    }

    @Test
    void handleUpdateComment_ReturnsUpdatedComment() {
        UUID id = UUID.randomUUID();
        Comment mockComment = new Comment("Not updated body", "ejatohvee", null);
        mockComment.setId(id);

        when(commentRepository.findById(id)).thenReturn(Optional.of(mockComment));

        commentService.updateComment(id, "New body");

        assertEquals("New body", mockComment.getBody());

        verify(commentRepository, times(1)).findById(id);
    }
}
