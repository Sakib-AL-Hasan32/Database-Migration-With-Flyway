package com.db_migration.product.dto.response;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        boolean active,
        LocalDateTime createdAt,
        String  createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
}
