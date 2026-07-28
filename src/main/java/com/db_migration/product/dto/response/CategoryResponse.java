package com.db_migration.product.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        boolean active
) {
}
