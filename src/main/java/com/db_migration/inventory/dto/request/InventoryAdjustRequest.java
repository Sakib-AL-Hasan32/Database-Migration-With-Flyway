package com.db_migration.inventory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InventoryAdjustRequest(
        @NotNull(message = "Product ID is required.")
        Long productId,

        @NotNull(message = "New quantity is required.")
        @Min(value = 0, message = "Quantity cannot be negative.")
        Integer quantity
) {
}
