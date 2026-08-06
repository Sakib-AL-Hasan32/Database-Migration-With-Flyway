package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiEndpoints {
    public static final String VERSION = "/api/v1";

    public static final class Auth{
        public static final String BASE = VERSION + "/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String REFRESH = "/refresh";
    }

    public static final class Product{
        public static final String BASE = VERSION + "/product";
        public static final String CREATE = "/create";
        public static final String GET_ALL = "/getAll";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class Category{
        public static final String BASE = VERSION + "/category";
        public static final String CREATE = "/create";
        public static final String GET_ALL = "/getAll";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class Inventory{
        public static final String BASE = VERSION + "/inventory";
        public static final String INCREASE = "/increase";
        public static final String DECREASE = "/decrease";
        public static final String RESERVE = "/reserve";
        public static final String RELEASE = "/release";
        public static final String GET_ALL = "/getAll";
        public static final String ADJUST = "/adjust";
    }

    public static final class Cart{
        public static final String BASE = VERSION + "/cart";
        public static final String ADD_ITEM = "/addItem";
        public static final String REMOVE_ITEM = "/removeItem/{id}";
        public static final String GET_ALL = "/getAll";
        public static final String INCREASE_QUANTITY = "/increaseQuantity/{id}";
        public static final String DECREASE_QUANTITY = "/decreaseQuantity/{id}";
        public static final String CLEAR_CART = "/clearCart";
    }

    public static final class Order{
        public static final String BASE = VERSION + "/order";
        public static final String PLACE_ORDER = "/placeOrder";
        public static final String CANCEL_ORDER = "/cancelOrder/{orderId}";
        public static final String GET_ALL = "/getAll";
    }

}
