package com.db_migration.auth.service;

import com.db_migration.auth.dto.request.LoginRequest;
import com.db_migration.auth.dto.request.RefreshTokenRequest;
import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.LoginResponse;
import com.db_migration.auth.dto.response.RefreshTokenResponse;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.common.response.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    ApiResponse<RegisterResponse> register(RegisterRequest registerRequest);
    ApiResponse<LoginResponse> login(LoginRequest loginRequest);
    ApiResponse<RefreshTokenResponse> refresh(RefreshTokenRequest refreshTokenRequest);
}
