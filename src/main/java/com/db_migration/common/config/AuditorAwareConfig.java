package com.db_migration.common.config;

import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AuditorAwareConfig {
    private final UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorAware() {
        return () -> {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null
                    || !authentication.isAuthenticated()
                    || authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }

            String username = authentication.getName();

            return userRepository.findByUsernameForAudit(username);
        };
    }
}
