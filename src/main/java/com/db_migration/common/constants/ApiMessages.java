package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiMessages {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Success {

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String USER_NOT_FOUND = "User not found";
    }
}
