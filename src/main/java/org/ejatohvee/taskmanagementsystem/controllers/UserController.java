package org.ejatohvee.taskmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.payloads.UpdateUserPayload;
import org.ejatohvee.taskmanagementsystem.security.UserDetailsManagerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableMethodSecurity
@RequestMapping("users/{username}")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManagerImpl userDetailsManager;

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or authentication.name == #username")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userDetailsManager.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or authentication.name == #username")
    public ResponseEntity<Void> updateUser(@PathVariable("username") String username, @RequestBody UpdateUserPayload payload) {
        userDetailsManager.updateUser(username, payload.username(), payload.email(), payload.password());
        return ResponseEntity.ok().build();
    }
}
