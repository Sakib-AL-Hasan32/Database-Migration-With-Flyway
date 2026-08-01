package com.db_migration.product.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String sku,
        boolean active,
        String categoryName
) {
}
