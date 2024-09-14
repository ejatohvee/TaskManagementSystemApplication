package org.ejatohvee.taskmanagementsystem.repositories;

import org.ejatohvee.taskmanagementsystem.entities.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Optional<Authority> findByAuthority(String authority);
}
