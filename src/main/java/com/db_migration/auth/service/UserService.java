package com.db_migration.auth.service;

import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.common.response.ApiResponse;

public interface UserService {
    ApiResponse<RegisterResponse> register(RegisterRequest registerRequest);
}
