package org.ejatohvee.taskmanagementsystemcore.repositories;

import org.ejatohvee.taskmanagementsystemcore.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
