package com.db_migration.auth.dto.response;

public record RegisterResponse (
        String username,
        String email
) {
}
