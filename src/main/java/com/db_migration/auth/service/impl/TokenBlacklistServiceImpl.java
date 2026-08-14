package com.db_migration.auth.service.impl;

import com.db_migration.auth.security.service.impl.RefreshTokenServiceImpl;
import com.db_migration.auth.service.TokenBlacklistService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final RefreshTokenServiceImpl refreshTokenServiceImpl;

    private final Cache<String, Instant> blacklist = Caffeine.newBuilder()
            .expireAfter(new Expiry<String, Instant>() {
                @Override
                @NullMarked
                public long expireAfterCreate(String key, Instant expiresAt, long currentTime) {
                    return Math.max(0, Duration.between(Instant.now(), expiresAt).toNanos());
                }

                @Override
                @NullMarked
                public long expireAfterUpdate(String key, Instant expiresAt, long currentTime, long currentDuration) {
                    return expireAfterCreate(key, expiresAt, currentTime);
                }

                @Override
                @NullMarked
                public long expireAfterRead(String key, Instant expiresAt, long currentTime, long currentDuration) {
                    return currentDuration;
                }
            })
            .build();

    @Override
    public void blacklist(String token, Duration ttl) {
        if (ttl.isZero() || ttl.isNegative()) {
            return;
        }
        blacklist.put(buildKey(token), Instant.now().plus(ttl));
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.getIfPresent(buildKey(token)) != null;
    }

    private String buildKey(String token) {
        return refreshTokenServiceImpl.generateHashToken(token);
    }
}
