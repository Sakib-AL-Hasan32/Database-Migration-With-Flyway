package com.db_migration.auth.dto.response;

import java.util.Set;

public record LoginResponse (
        String username,
        Set<String> roles,
        String accessToken,
        String refreshToken
) {
}
