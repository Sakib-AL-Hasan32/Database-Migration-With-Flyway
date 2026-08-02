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
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
        public static final String INVALID_ROLE = "Invalid role";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
        public static final String CATEGORY_NOT_FOUND = "Category not found";
        public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
        public static final String PRODUCT_NOT_FOUND = "Product not found";
    }
}
