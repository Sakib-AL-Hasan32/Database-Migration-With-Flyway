package com.db_migration.order.dto.response;

import com.db_migration.order.entity.OrderStatus;
import com.db_migration.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String orderNumber,
        OrderStatus status,
        BigDecimal totalAmount,
        String shippingAddress,
        PaymentMethod paymentMethod,
        List<OrderItemResponse> items,
        LocalDateTime createdAt
) {
}
