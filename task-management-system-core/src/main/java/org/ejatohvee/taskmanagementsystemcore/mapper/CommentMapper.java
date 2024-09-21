package org.ejatohvee.taskmanagementsystemcore.mapper;


import org.ejatohvee.taskmanagementsystemcore.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystemcore.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO commentToCommentDto(Comment comment);
}
