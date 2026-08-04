package com.db_migration.order.controller;

import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.order.dto.request.OrderCreateRequest;
import com.db_migration.order.dto.response.OrderResponse;
import com.db_migration.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Order.BASE)
public class OrderController {
    private final OrderService orderService;

    @PostMapping(ApiEndpoints.Order.PLACE_ORDER)
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody OrderCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(userDetails, request));
    }

    @PatchMapping(ApiEndpoints.Order.CANCEL_ORDER)
    public ResponseEntity<ApiResponse<Void>> cancelOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(userDetails, orderId));
    }

    @GetMapping(ApiEndpoints.Order.GET_ALL)
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll(userDetails));
    }
}
