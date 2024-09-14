package org.ejatohvee.taskmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.entities.User;
import org.ejatohvee.taskmanagementsystem.payloads.NewUserPayload;
import org.ejatohvee.taskmanagementsystem.payloads.UpdateUserPayload;
import org.ejatohvee.taskmanagementsystem.repositories.UserRepository;
import org.ejatohvee.taskmanagementsystem.security.entities.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsManagerImpl implements UserDetailsManager {

    private final AuthoritiesService authoritiesService;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User %s not found.".formatted(username)));
        return new SecurityUser(user);
    }

    public void createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(password));
        user.setAuthorities(List.of(authoritiesService.getUserAuthority()));
        userRepository.save(user);
    }

    public void updateUser(String username, String newUsername, String newEmail, String newPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User %s not found".formatted(username))
        );

        if (newUsername != null && !newUsername.trim().isEmpty()) {
            user.setUsername(newUsername);
        }

        if (newEmail != null && !newEmail.trim().isEmpty()) {
            user.setEmail(newEmail);
        }

        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoderConfig.passwordEncoder().encode(newPassword));
        }

        userRepository.save(user);
    }


    @Override
    public void updateUser(UserDetails userDetails) {
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        SecurityUser user = (SecurityUser) loadUserByUsername(username);
        userRepository.deleteById(user.getId());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public void createUser(UserDetails userDetails) {
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

}
