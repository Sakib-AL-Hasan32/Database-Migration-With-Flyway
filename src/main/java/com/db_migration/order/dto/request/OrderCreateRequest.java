package com.db_migration.order.dto.request;

import com.db_migration.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public record OrderCreateRequest(
        @NotNull(message = "Product is required.")
        String shippingAddress,

        @NotNull(message = "Product is required.")
        PaymentMethod paymentMethod
) {
}
