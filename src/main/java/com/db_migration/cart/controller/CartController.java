package com.db_migration.cart.controller;

import com.db_migration.cart.dto.request.CartItemCreateRequest;
import com.db_migration.cart.dto.request.CartItemUpdateRequest;
import com.db_migration.cart.dto.response.CartResponse;
import com.db_migration.cart.service.CartService;
import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Cart.BASE)
public class CartController {
    private final CartService cartService;

    @PostMapping(ApiEndpoints.Cart.ADD_ITEM)
    public ResponseEntity<ApiResponse<CartResponse>> addItem(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CartItemCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addItem(request, userDetails));
    }

    @DeleteMapping(ApiEndpoints.Cart.REMOVE_ITEM)
    public ResponseEntity<ApiResponse<CartResponse>> removeItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.removeItem(userDetails, id));
    }

    @GetMapping(ApiEndpoints.Cart.GET_ALL)
    public ResponseEntity<ApiResponse<CartResponse>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAll(userDetails));
    }

    @PutMapping(ApiEndpoints.Cart.INCREASE_QUANTITY)
    public ResponseEntity<ApiResponse<CartResponse>> increaseQuantity(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @Valid @RequestBody CartItemUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.increaseQuantity(userDetails, id, request));
    }

    @PutMapping(ApiEndpoints.Cart.DECREASE_QUANTITY)
    public ResponseEntity<ApiResponse<CartResponse>> decreaseQuantity(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @Valid @RequestBody CartItemUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.decreaseQuantity(userDetails, id, request));
    }

    @DeleteMapping(ApiEndpoints.Cart.CLEAR_CART)
    public ResponseEntity<ApiResponse<Void>> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.clearCart(userDetails));
    }
}
