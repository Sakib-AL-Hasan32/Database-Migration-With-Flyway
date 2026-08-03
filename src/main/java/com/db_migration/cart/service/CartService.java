package com.db_migration.cart.service;

import com.db_migration.cart.dto.request.CartItemCreateRequest;
import com.db_migration.cart.dto.response.CartResponse;
import com.db_migration.common.response.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface CartService {
    ApiResponse<CartResponse> addItem(CartItemCreateRequest request, UserDetails userDetails);
}
