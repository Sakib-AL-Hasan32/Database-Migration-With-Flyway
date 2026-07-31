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
    }

    public static final class Product{
        public static final String BASE = VERSION + "/product";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
    }

    public static final class Category{
        public static final String BASE = VERSION + "/category";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{id}";
        public static final String GET_ALL = "/getAll";
        public static final String DELETE = "/delete/{id}";
    }

}
