package org.ejatohvee.taskmanagementsystem.repositories;

import org.ejatohvee.taskmanagementsystem.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
