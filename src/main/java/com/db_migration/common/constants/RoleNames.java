package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleNames {
    public static final String ADMIN = "ADMIN";
    public static final String CUSTOMER = "CUSTOMER";
    public static final String PRODUCT_MANAGER = "PRODUCT_MANAGER";
    public static final String INVENTORY_MANAGER = "INVENTORY_MANAGER";
    public static final String ORDER_MANAGER = "ORDER_MANAGER";
}
