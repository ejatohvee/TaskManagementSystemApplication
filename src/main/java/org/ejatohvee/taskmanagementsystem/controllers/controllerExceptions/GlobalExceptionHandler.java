package org.ejatohvee.taskmanagementsystem.controllers.controllerExceptions;

import org.ejatohvee.taskmanagementsystem.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("Not found: " + e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException() {
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
    }
}
