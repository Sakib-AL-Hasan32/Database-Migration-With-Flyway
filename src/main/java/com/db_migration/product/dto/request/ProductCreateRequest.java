package com.db_migration.product.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductCreateRequest (
        @NotBlank(message = "Product name is required.")
        @Size(max = 150, message = "Product name cannot exceed 150 characters.")
        String name,

        @Size(max = 1000, message = "Description cannot exceed 1000 characters.")
        String description,

        @NotNull(message = "Price is required.")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
        BigDecimal price,

        @NotNull(message = "Stock quantity is required.")
        @Min(value = 0, message = "Stock quantity cannot be negative.")
        Integer stockQuantity,

        @NotBlank(message = "SKU is required.")
        @Size(max = 50, message = "SKU cannot exceed 50 characters.")
        String sku,

        boolean active,

        @NotNull(message = "Category is required.")
        Long categoryId
) {
}
