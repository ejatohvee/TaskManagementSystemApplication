package org.ejatohvee.taskmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystem.payloads.NewUserPayload;
import org.ejatohvee.taskmanagementsystem.security.AuthService;
import org.ejatohvee.taskmanagementsystem.security.entities.JwtRequest;
import org.ejatohvee.taskmanagementsystem.security.entities.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        return authService.loginUser(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody NewUserPayload payload) {
        return authService.registerUser(payload);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(name = "refreshToken", required = false) String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}