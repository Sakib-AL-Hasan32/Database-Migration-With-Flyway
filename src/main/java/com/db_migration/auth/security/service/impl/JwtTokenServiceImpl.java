package com.db_migration.auth.security.service.impl;

import com.db_migration.auth.entity.Role;
import com.db_migration.auth.entity.User;
import com.db_migration.auth.security.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.secret-key}")
    private String SecretKey;

    @Value("${jwt.access-token-expiration}")
    private Duration accessTokenExpiration;

    public SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateAccessToken(User user) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + accessTokenExpiration.toMillis());

        Set<String> roles = new LinkedHashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }

        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "roles", roles
        );

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateSecretKey())
                .compact();
    }

    @Override
    public Claims extractClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = extractClaimsFromToken(token);
        return claims.getSubject();

    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String usernameFromToken = getUsernameFromToken(token);
        return usernameFromToken.equals(userDetails.getUsername());
    }
}
