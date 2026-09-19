package com.db_migration.auth.controller;

import com.db_migration.auth.dto.request.LoginRequest;
import com.db_migration.auth.dto.request.LogoutRequest;
import com.db_migration.auth.dto.request.RefreshTokenRequest;
import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.LoginResponse;
import com.db_migration.auth.dto.response.RefreshTokenResponse;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.auth.service.AuthService;
import com.db_migration.common.constants.ApiEndpoints;
import com.db_migration.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.Auth.BASE)
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register user",
            description = "Creates a new active user account with a BCrypt encrypted password.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "User registration payload containing identity and credential details.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegisterRequest.class),
                            examples = @ExampleObject(
                                    name = "Register user",
                                    value = """
                                        {
                                          "firstName": "Sakib",
                                          "lastName": "Al Hasan",
                                          "username": "sakib",
                                          "email": "sakib@gmail.com",
                                          "phoneNumber": "+8801700000000",
                                          "password": "StrongPass123"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "User registered successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Successful registration",
                                            value = """
                                              {
                                                "data": {
                                                    "username": "sakib",
                                                    "email": "sakib@gmail.com"
                                                },
                                                "message": "Register successfully!"
                                              }
                                            """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid registration payload",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "409",
                            description = "Username or email already exists",
                            content = @Content
                    )
            }
    )
    @PostMapping(ApiEndpoints.Auth.REGISTER)
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(registerRequest));
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates a user using valid credentials and returns an access token and refresh token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "User login credentials.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(
                                    name = "Login user",
                                    value = """
                                        {
                                          "username": "sakib",
                                          "password": "StrongPass123"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "User logged in successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Successful login",
                                            value = """
                                              {
                                                "data": {
                                                  "username": "sakib",
                                                  "roles": [
                                                    "ADMIN"
                                                  ],
                                                  "accessToken": "eyJhbGciOiJk..."
                                                },
                                                "message": "Login successfully!"
                                             }
                                            """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid login payload",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Invalid username or password",
                            content = @Content
                    )
            }
    )
    @PostMapping(ApiEndpoints.Auth.LOGIN)
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @Operation(
            summary = "Refresh token",
            description = "Generates a new access token and refresh token using a valid refresh token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Refresh token used to generate a new access token and a new refresh token.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RefreshTokenRequest.class),
                            examples = @ExampleObject(
                                    name = "Refresh token",
                                    value = """
                                        {
                                          "refreshToken": "your-refresh-token",
                                          "accessToken": "your-access-token"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Access token refreshed successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Token Refresh",
                                            value = """
                                              {
                                                "data": {
                                                  "accessToken": "eyJhbGciOi...",
                                                  "refreshToken": "ADhZb4M7kFC4"
                                                },
                                                "message": "Token refreshed"
                                              }
                                            """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid refresh token request",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Invalid or expired refresh token",
                            content = @Content
                    )
            }
    )
    @PostMapping(ApiEndpoints.Auth.REFRESH)
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refresh(refreshTokenRequest));
    }

    @Operation(
            summary = "Logout user",
            description = "Logs out the authenticated user by invalidating the provided access token and refresh token.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Refresh token associated with the current authentication session.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LogoutRequest.class),
                            examples = @ExampleObject(
                                    name = "Logout request",
                                    value = """
                                        {
                                          "refreshToken": "your-refresh-token"
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "User logged out successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            name = "Successful logout",
                                            value = """
                                              {
                                                "message": "Logout successfully!"
                                              }
                                            """
                                    )
                            )
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Invalid logout request",
                            content = @Content
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Invalid or expired access token or refresh token",
                            content = @Content
                    )
            }
    )
    @PostMapping(ApiEndpoints.Auth.LOGOUT)
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(name = "Authorization") String authorizationHeader, @Valid @RequestBody LogoutRequest logoutRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.logout(authorizationHeader, logoutRequest));
    }
}
