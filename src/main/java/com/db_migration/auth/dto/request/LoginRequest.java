package com.db_migration.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
        @NotBlank(message = "Username can not be blanked")
        String username,

        @NotBlank(message = "Password can not be blanked")
        String password
) {
}
