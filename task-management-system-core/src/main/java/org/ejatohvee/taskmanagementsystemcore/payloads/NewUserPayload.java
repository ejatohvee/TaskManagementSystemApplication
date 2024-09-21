package org.ejatohvee.taskmanagementsystemcore.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserPayload(@Size(min = 3, max = 20, message = "{api.auth.register.errors.username.size}") String username,
                             @NotNull String email,
                             @Size(min = 5, message = "{api.auth.register.errors.username.size}") String password, @NotNull String confirmPassword) {
}
