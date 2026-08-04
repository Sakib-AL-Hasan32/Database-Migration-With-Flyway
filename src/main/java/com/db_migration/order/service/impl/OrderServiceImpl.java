package com.db_migration.order.service.impl;

import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.UserRepository;
import com.db_migration.cart.entity.Cart;
import com.db_migration.cart.entity.CartItem;
import com.db_migration.cart.repository.CartItemRepository;
import com.db_migration.cart.repository.CartRepository;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceNotFound;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.inventory.entity.Inventory;
import com.db_migration.inventory.repository.InventoryRepository;
import com.db_migration.order.dto.request.OrderCreateRequest;
import com.db_migration.order.dto.response.OrderItemResponse;
import com.db_migration.order.dto.response.OrderResponse;
import com.db_migration.order.entity.Order;
import com.db_migration.order.entity.OrderItem;
import com.db_migration.order.entity.OrderStatus;
import com.db_migration.order.repository.OrderItemRepository;
import com.db_migration.order.repository.OrderRepository;
import com.db_migration.order.service.OrderService;
import com.db_migration.product.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('" + PermissionNames.PLACE_ORDER + "')")
    public ApiResponse<OrderResponse> placeOrder(UserDetails userDetails, OrderCreateRequest request) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(ApiMessages.Error.USER_NOT_FOUND));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.CART_NOT_FOUND));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        // Validation Check
        if(cartItems.isEmpty()) {
            throw new IllegalArgumentException(ApiMessages.Error.CART_EMPTY);
        }

        for(CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            if(!product.isActive()) {
                throw new IllegalArgumentException(ApiMessages.Error.PRODUCT_NOT_ACTIVATED);
            }
            Inventory inventory = inventoryRepository.findByProductId(product.getId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.INVENTORY_NOT_FOUND));

            if(inventory.getAvailableQuantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException(ApiMessages.Error.INSUFFICIENT_QUANTITY);
            }
        }

        // Total Amount Calculated
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            totalAmount = totalAmount.add(
                    cartItem.getProduct()
                            .getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
        }

        // Order Object Created
        Order order = Order.builder()
                .orderNumber(generateOrderNumber())
                .user(user)
                .status(OrderStatus.PENDING)
                .totalAmount(totalAmount)
                .shippingAddress(request.shippingAddress())
                .paymentMethod(request.paymentMethod())
                .build();
        orderRepository.save(order);

        List<OrderItemResponse> orderItemResponseList = new ArrayList<>();

        // OrderItem Object Created
        // OrderItemResponse Created
        // Inventory Updated
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .unitPrice(product.getPrice())
                    .build();
            orderItemRepository.save(orderItem);

            Inventory inventory = inventoryRepository.findByProductId(cartItem.getProduct().getId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.INVENTORY_NOT_FOUND));

            inventory.setTotalQuantity(inventory.getTotalQuantity() - cartItem.getQuantity());

            inventoryRepository.save(inventory);

            BigDecimal subTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    orderItem.getId(),
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    orderItem.getQuantity(),
                    subTotal
            );
            orderItemResponseList.add(orderItemResponse);
        }

        // Clear CartItems [Not the cart]
        cartItemRepository.deleteAll(cartItems);

        OrderResponse response = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getShippingAddress(),
                order.getPaymentMethod(),
                orderItemResponseList,
                order.getCreatedAt()
        );

        return ApiResponse.<OrderResponse>builder()
                .data(response)
                .message(ApiMessages.Success.ORDER_CONFIRMED)
                .build();
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }
}
