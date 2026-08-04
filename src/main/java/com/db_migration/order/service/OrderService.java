package com.db_migration.order.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.order.dto.request.OrderCreateRequest;
import com.db_migration.order.dto.response.OrderResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OrderService {
    ApiResponse<OrderResponse> placeOrder(UserDetails userDetails, OrderCreateRequest request);
    ApiResponse<Void> cancelOrder(UserDetails userDetails, Long orderId);
    ApiResponse<List<OrderResponse>> getAll(UserDetails userDetails);
}
