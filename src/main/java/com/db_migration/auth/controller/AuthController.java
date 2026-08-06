package com.db_migration.auth.controller;

import com.db_migration.auth.dto.request.LoginRequest;
import com.db_migration.auth.dto.request.RefreshTokenRequest;
import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.LoginResponse;
import com.db_migration.auth.dto.response.RefreshTokenResponse;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.auth.service.AuthService;
import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Auth.BASE)
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiEndpoints.Auth.REGISTER)
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(registerRequest));
    }

    @PostMapping(ApiEndpoints.Auth.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @PostMapping(ApiEndpoints.Auth.REFRESH)
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refresh(refreshTokenRequest));
    }
}
