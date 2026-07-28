package com.db_migration.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiMessages {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Success {
        public static final String REGISTER_SUCCESS = "Register successfully!";
        public static final String LOGIN_SUCCESS = "Login successfully!";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Error {
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
        public static final String INVALID_ROLE = "Invalid role";
    }
}
