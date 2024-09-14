package org.ejatohvee.taskmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.Authority;
import org.ejatohvee.taskmanagementsystem.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
