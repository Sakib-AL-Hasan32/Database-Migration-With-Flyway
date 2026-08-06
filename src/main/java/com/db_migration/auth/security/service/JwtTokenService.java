package com.db_migration.auth.security.service;

import com.db_migration.auth.entity.RefreshToken;
import com.db_migration.auth.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String generateAccessToken(User user);
    Claims extractClaimsFromToken(String token);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

}
