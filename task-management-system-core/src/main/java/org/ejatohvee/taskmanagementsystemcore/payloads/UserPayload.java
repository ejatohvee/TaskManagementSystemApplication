package org.ejatohvee.taskmanagementsystemcore.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserPayload(@NotNull UUID id,
                          @NotNull String username,
                          @NotNull String email,
                          @NotNull String password) {
}
