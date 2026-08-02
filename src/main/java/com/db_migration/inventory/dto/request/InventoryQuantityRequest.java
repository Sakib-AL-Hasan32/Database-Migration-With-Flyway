package com.db_migration.inventory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InventoryQuantityRequest(

        @NotNull(message = "Product is required.")
        Long productId,

        @NotNull(message = "Total quantity is required.")
        @Min(value = 1, message = "Quantity must be greater than 0.")
        Integer quantity

) {
}
