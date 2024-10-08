package org.ejatohvee.taskmanagementsystemcore.repositories;

import org.ejatohvee.taskmanagementsystemcore.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
    Task getTaskById(UUID id);

    List<Task> getTasksByAuthor(String author);
}
