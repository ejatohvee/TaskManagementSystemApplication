package org.ejatohvee.taskmanagementsystem.dtos;


import java.util.UUID;

public record CommentDTO (UUID id, String body, String author, String time) {}
