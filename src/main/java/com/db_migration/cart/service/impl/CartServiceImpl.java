package com.db_migration.cart.service.impl;

import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.UserRepository;
import com.db_migration.cart.dto.request.CartItemCreateRequest;
import com.db_migration.cart.dto.response.CartItemResponse;
import com.db_migration.cart.dto.response.CartResponse;
import com.db_migration.cart.entity.Cart;
import com.db_migration.cart.entity.CartItem;
import com.db_migration.cart.repository.CartItemRepository;
import com.db_migration.cart.repository.CartRepository;
import com.db_migration.cart.service.CartService;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.PermissionNames;
import com.db_migration.common.exception.ResourceNotFound;
import com.db_migration.common.response.ApiResponse;
import com.db_migration.inventory.entity.Inventory;
import com.db_migration.inventory.repository.InventoryRepository;
import com.db_migration.product.entity.Product;
import com.db_migration.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasAuthority('" + PermissionNames.ADD_ITEM_TO_CART + "')")
    public ApiResponse<CartResponse> addItem(CartItemCreateRequest request, UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.USER_NOT_FOUND));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = Cart.builder()
                    .user(user)
                    .build();
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.PRODUCT_NOT_FOUND));

        if(!product.isActive()) {
            throw new IllegalArgumentException(ApiMessages.Error.PRODUCT_NOT_ACTIVATED);
        }

        Inventory inventory = inventoryRepository.findByProductId(request.productId()).orElseThrow(() -> new ResourceNotFound(ApiMessages.Error.INVENTORY_NOT_FOUND));

        if (inventory.getAvailableQuantity() < request.quantity()) {
            throw new IllegalArgumentException(ApiMessages.Error.INSUFFICIENT_QUANTITY);
        }

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if(cartItem.isPresent()) {
            CartItem existingCartItem = cartItem.get();
            int newQuantity = existingCartItem.getQuantity() + request.quantity();
            if(newQuantity > inventory.getAvailableQuantity()) {
                throw new IllegalArgumentException(ApiMessages.Error.INSUFFICIENT_QUANTITY);
            }
            existingCartItem.setQuantity(newQuantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.quantity())
                    .build();
            cartItemRepository.save(newCartItem);
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<CartItemResponse>  cartItemResponseList = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        for(CartItem item : cartItems) {
            BigDecimal subTotal = item.getProduct()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(subTotal);

            CartItemResponse cartItemResponse = new CartItemResponse(
                    item.getId(),
                    item.getProduct().getId(),
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    subTotal
            );

            cartItemResponseList.add(cartItemResponse);
        }

        CartResponse response = new CartResponse(
                cart.getId(),
                cartItemResponseList,
                totalPrice
        );

        return ApiResponse.<CartResponse>builder()
                .data(response)
                .message(ApiMessages.Success.ITEM_ADDED)
                .build();
    }
}
