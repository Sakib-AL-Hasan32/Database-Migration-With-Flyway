package com.db_migration.cart.controller;

import com.db_migration.cart.dto.request.CartItemCreateRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Cart.BASE)
public class CartController {
    private final CartService cartService;

    @PostMapping(ApiEndpoints.Cart.ADD_ITEM)
    public ResponseEntity<ApiResponse<CartResponse>> addItem(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CartItemCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addItem(request, userDetails));
    }
}
