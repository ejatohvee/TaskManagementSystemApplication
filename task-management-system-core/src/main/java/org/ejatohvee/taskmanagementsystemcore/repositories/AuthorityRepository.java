package org.ejatohvee.taskmanagementsystemcore.repositories;


import org.ejatohvee.taskmanagementsystemcore.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Optional<Authority> findByAuthority(String authority);
}
