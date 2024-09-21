package org.ejatohvee.taskmanagementsystemsecurity.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystemcore.entities.Authority;
import org.ejatohvee.taskmanagementsystemcore.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService{
    private final AuthorityRepository authorityRepository;

    @Override
    public Authority getUserAuthority() {
        return authorityRepository.findByAuthority("ROLE_USER").get();
    }
}
