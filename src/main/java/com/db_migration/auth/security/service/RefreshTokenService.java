package com.db_migration.auth.security.service;

import com.db_migration.auth.entity.RefreshToken;
import com.db_migration.auth.entity.User;

public interface RefreshTokenService {
    String generateRefreshToken(User user);
    RefreshToken getValidRefreshToken(String token);
}
