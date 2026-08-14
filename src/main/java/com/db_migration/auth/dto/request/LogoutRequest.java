package com.db_migration.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
        @NotBlank(message = "Refresh token can not be blanked")
        String refreshToken
) {
}
