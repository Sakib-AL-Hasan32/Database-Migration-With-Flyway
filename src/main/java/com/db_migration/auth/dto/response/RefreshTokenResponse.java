package com.db_migration.auth.dto.response;

public record RefreshTokenResponse(
        String refreshToken,
        String accessToken
) {
}
