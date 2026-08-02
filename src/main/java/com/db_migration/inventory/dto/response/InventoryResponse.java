package com.db_migration.inventory.dto.response;

import java.time.LocalDateTime;

public record InventoryResponse(
        Long id,
        Long productId,
        String productName,
        Integer totalQuantity,
        Integer reservedQuantity,
        Integer availableQuantity,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
}
