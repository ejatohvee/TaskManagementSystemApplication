package org.ejatohvee.taskmanagementsystemcore.dtos;


import java.util.UUID;

public record CommentDTO (UUID id, String body, String author, String time) {}
