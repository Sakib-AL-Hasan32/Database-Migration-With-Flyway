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

}
