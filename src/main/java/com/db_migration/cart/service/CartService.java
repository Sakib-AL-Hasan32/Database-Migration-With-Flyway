package com.db_migration.cart.service;

import com.db_migration.cart.dto.request.CartItemCreateRequest;
import com.db_migration.cart.dto.request.CartItemUpdateRequest;
import com.db_migration.cart.dto.response.CartResponse;
import com.db_migration.common.response.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface CartService {
    ApiResponse<CartResponse> addItem(CartItemCreateRequest request, UserDetails userDetails);
    ApiResponse<CartResponse> removeItem(UserDetails userDetails, Long id);
    ApiResponse<CartResponse> getAll(UserDetails userDetails);
    ApiResponse<CartResponse> increaseQuantity(UserDetails userDetails, Long id, CartItemUpdateRequest request);
    ApiResponse<CartResponse> decreaseQuantity(UserDetails userDetails, Long id, CartItemUpdateRequest request);
    ApiResponse<Void> clearCart(UserDetails userDetails);
}
