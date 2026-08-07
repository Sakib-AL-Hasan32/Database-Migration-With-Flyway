package com.db_migration.auth.service.impl;

import com.db_migration.auth.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenCleanupService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(cron = "${refresh-token.cleanup.cron}")
    @Transactional
    public void removeExpiredRefreshTokens() {

        log.info("Starting expired refresh token cleanup...");

        refreshTokenRepository.deleteByExpiresAtBefore(LocalDateTime.now());

        log.info("Expired refresh token cleanup completed.");
    }
}
