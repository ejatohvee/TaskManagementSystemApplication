package org.ejatohvee.taskmanagementsystem.mapper;

import org.ejatohvee.taskmanagementsystem.dtos.CommentDTO;
import org.ejatohvee.taskmanagementsystem.entities.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO commentToCommentDto(Comment comment);
}
