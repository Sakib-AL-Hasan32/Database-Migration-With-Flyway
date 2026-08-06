package com.db_migration.auth.security.service.impl;

import com.db_migration.auth.entity.RefreshToken;
import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.RefreshTokenRepository;
import com.db_migration.auth.security.service.RefreshTokenService;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.exception.InvalidTokenException;
import com.db_migration.common.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private static final int REFRESH_TOKEN_BYTES = 64;
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    private final SecureRandom secureRandom = new SecureRandom();
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private Duration refreshTokenExpiration;

    public String generateRawToken(){
        byte[] tokenBytes = new byte[REFRESH_TOKEN_BYTES];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public String generateHashToken(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            char[] hexChars = new char[hash.length * 2];
            for (int i = 0; i < hash.length; i++) {
                int value = hash[i] & 0xFF;
                hexChars[i * 2] = HEX_ARRAY[value >>> 4];
                hexChars[i * 2 + 1] = HEX_ARRAY[value & 0x0F];
            }
            return new String(hexChars);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 algorithm is not available.", exception);
        }
    }

    @Override
    public String generateRefreshToken(User user) {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiresAt = LocalDateTime.now().plus(refreshTokenExpiration);

        String rawToken = generateRawToken();
        String hashToken = generateHashToken(rawToken);

        RefreshToken refreshToken = RefreshToken.builder()
                .tokenHash(hashToken)
                .createdAt(createdAt)
                .expiresAt(expiresAt)
                .user(user)
                .build();
        refreshTokenRepository.save(refreshToken);

        return rawToken;
    }

    @Override
    public RefreshToken getValidRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(generateHashToken(token)).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.REFRESH_TOKEN_NOT_FOUND));
        if(refreshToken.isRevoked()) {
            throw new InvalidTokenException(ApiMessages.Error.REFRESH_TOKEN_REVOKED);
        }
        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException(ApiMessages.Error.REFRESH_TOKEN_EXPIRED);
        }
        return refreshToken;
    }
}
