package com.db_migration.order.service;

import com.db_migration.common.response.ApiResponse;
import com.db_migration.order.dto.request.OrderCreateRequest;
import com.db_migration.order.dto.response.OrderResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderService {
    ApiResponse<OrderResponse> placeOrder(UserDetails userDetails, OrderCreateRequest request);
}
