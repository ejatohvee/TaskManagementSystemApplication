package org.ejatohvee.taskmanagementsystemsecurity.security;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.ejatohvee.taskmanagementsystemsecurity.security.entities.JwtRequest;
import org.ejatohvee.taskmanagementsystemsecurity.security.entities.JwtResponse;
import org.ejatohvee.taskmanagementsystemcore.payloads.NewUserPayload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsManagerImpl userDetailsManager;
    private final JwtTokenUtils jwtTokenUtils;
    @Resource
    private AuthenticationManager authenticationManager;

    public ResponseEntity<JwtResponse> loginUser(JwtRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails userDetails = userDetailsManager.loadUserByUsername(request.username());
        String accessToken = jwtTokenUtils.generateToken(userDetails);

        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
        ResponseCookie refreshCookie = jwtTokenUtils.createRefreshCookie(refreshToken);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshCookie.toString()).body(new JwtResponse(accessToken));
    }

    public ResponseEntity<String> registerUser(NewUserPayload payload) {
        if (userDetailsManager.findByUsername(payload.username()).isPresent()) {
            return new ResponseEntity<>("User with such name already exists", HttpStatus.BAD_REQUEST);
        }
        if (!payload.password().equals(payload.confirmPassword())) {
            return new ResponseEntity<>("Passwords don't not match", HttpStatus.BAD_REQUEST);
        }

        userDetailsManager.createUser(payload.username(), payload.email(), payload.password());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> refreshToken(String refreshToken) {
        if (refreshToken == null || !jwtTokenUtils.validateToken(refreshToken)) {
            return new ResponseEntity<>("Invalid refresh token", HttpStatus.UNAUTHORIZED);
        }

        String username = jwtTokenUtils.getUsername(refreshToken);
        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        String accessToken = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }
}
