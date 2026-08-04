package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PermissionNames {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String UPDATE_PRODUCT = "UPDATE_PRODUCT";
    public static final String DELETE_PRODUCT = "DELETE_PRODUCT";
    public static final String VIEW_PRODUCT = "VIEW_PRODUCT";

    public static final String CREATE_CATEGORY = "CREATE_CATEGORY";
    public static final String UPDATE_CATEGORY = "UPDATE_CATEGORY";
    public static final String DELETE_CATEGORY = "DELETE_CATEGORY";
    public static final String VIEW_CATEGORY = "VIEW_CATEGORY";

    public static final String CREATE_CART = "CREATE_CART";
    public static final String UPDATE_CART = "UPDATE_CART";
    public static final String DELETE_CART = "DELETE_CART";
    public static final String VIEW_CART = "VIEW_CART";

    public static final String CREATE_CART_ITEM = "CREATE_CART_ITEM";
    public static final String UPDATE_CART_ITEM = "UPDATE_CART_ITEM";
    public static final String VIEW_CART_ITEM = "VIEW_CART_ITEM";
    public static final String DELETE_CART_ITEM = "DELETE_CART_ITEM";
    public static final String ADD_ITEM_TO_CART = "ADD_ITEM_TO_CART";
    public static final String REMOVE_ITEM_FROM_CART = "REMOVE_ITEM_FROM_CART";

    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String UPDATE_ORDER = "UPDATE_ORDER";
    public static final String DELETE_ORDER = "DELETE_ORDER";
    public static final String VIEW_ORDER = "VIEW_ORDER";

    public static final String VIEW_INVENTORY = "VIEW_INVENTORY";
    public static final String INCREASE_INVENTORY = "INCREASE_INVENTORY";
    public static final String DECREASE_INVENTORY = "DECREASE_INVENTORY";
    public static final String RESERVE_INVENTORY = "RESERVE_INVENTORY";
    public static final String RELEASE_INVENTORY = "RELEASE_INVENTORY";
    public static final String ADJUST_INVENTORY = "ADJUST_INVENTORY";
}
