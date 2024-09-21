package org.ejatohvee.taskmanagementsystemcore.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserPayload(@Size(min = 3, max = 20, message = "{users.update.errors.username.wrong_username_length}") String username,
                                @NotNull String email,
                                @Size(min = 5, message = "{users.update.errors.password.size}") String password) {
}
