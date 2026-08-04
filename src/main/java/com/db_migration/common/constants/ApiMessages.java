package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiMessages {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Success {
        public static final String REGISTER_SUCCESS = "Register successfully!";
        public static final String LOGIN_SUCCESS = "Login successfully!";
        public static final String CATEGORY_CREATED = "Category created!";
        public static final String CATEGORY_FETCHED = "Category fetched!";
        public static final String CATEGORY_UPDATED = "Category updated!";
        public static final String CATEGORY_DELETED = "Category deleted!";
        public static final String PRODUCT_CREATED = "Product created!";
        public static final String PRODUCT_FETCHED = "Product fetched!";
        public static final String PRODUCT_UPDATED = "Product updated!";
        public static final String PRODUCT_DELETED = "Product deleted!";
        public static final String INVENTORY_INCREASED = "Inventory increased";
        public static final String  INVENTORY_DECREASED = "Inventory decreased";
        public static final String INVENTORY_RESERVED = "Inventory reserved";
        public static final String INVENTORY_RELEASED = "Inventory released";
        public static final String INVENTORY_FETCHED = "Inventory fetched";
        public static final String INVENTORY_ADJUSTED = "Inventory adjusted";
        public static final String ITEM_ADDED = "Item added";
        public static final String ITEM_DELETED = "Item deleted";
        public static final String CART_FETCHED = "Cart fetched";
        public static final String CART_UPDATED = "Cart updated";
        public static final String CART_DELETED = "Cart deleted";
        public static final String ORDER_CONFIRMED = "Order confirmed";
        public static final String ORDER_CANCELLED = "Order cancelled";
        public static final String ORDER_FETCHED = "Order fetched";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String NOT_FOUND = "Not found!";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
        public static final String INVALID_ROLE = "Invalid role";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
        public static final String CATEGORY_NOT_FOUND = "Category not found";
        public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
        public static final String PRODUCT_NOT_FOUND = "Product not found";
        public static final String INVENTORY_NOT_FOUND = "Inventory not found";
        public static final String INVENTORY_DECREASED_FAILED = "Inventory decreased failed";
        public static final String INVENTORY_RESERVED_FAILED = "Inventory reserved failed";
        public static final String INVENTORY_RELEASED_FAILED = "Inventory released failed";
        public static final String PRODUCT_NOT_ACTIVATED = "Product not activated";
        public static final String INSUFFICIENT_QUANTITY = "Insufficient quantity";
        public static final String CART_NOT_FOUND = "Cart not found";
        public static final String CART_EMPTY = "Cart empty";
        public static final String ORDER_NOT_FOUND = "Order not found";
        public static final String ACCESS_DENIED = "Access denied";
        public static final String ORDER_CANNOT_BE_CANCELLED = "Order cannot be cancelled";
    }
}
