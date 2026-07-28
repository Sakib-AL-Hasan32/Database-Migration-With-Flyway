package com.db_migration.auth.service.impl;

import com.db_migration.auth.dto.request.LoginRequest;
import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.LoginResponse;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.auth.entity.Role;
import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.RoleRepository;
import com.db_migration.auth.repository.UserRepository;
import com.db_migration.auth.security.service.CustomUserDetailsService;
import com.db_migration.auth.security.service.JwtTokenService;
import com.db_migration.auth.security.service.RefreshTokenService;
import com.db_migration.auth.service.AuthService;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.constants.RoleNames;
import com.db_migration.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenService refreshTokenService;
    private final RoleRepository roleRepository;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public ApiResponse<RegisterResponse> register(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new IllegalArgumentException(ApiMessages.Error.USER_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new IllegalArgumentException(ApiMessages.Error.EMAIL_ALREADY_EXISTS);
        }

        Set<Role> roles = new LinkedHashSet<>();
        Role role = roleRepository.findByName(RoleNames.CUSTOMER)
                .orElseThrow(() -> new IllegalArgumentException(ApiMessages.Error.INVALID_ROLE));
        roles.add(role);

        User user = User.builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .roles(roles)
                .build();
        userRepository.save(user);

        RegisterResponse response = new RegisterResponse(
                registerRequest.username(),
                registerRequest.email()
        );
        return ApiResponse.<RegisterResponse>builder()
                .data(response)
                .message(ApiMessages.Success.REGISTER_SUCCESS)
                .build();
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(ApiMessages.Error.USER_NOT_FOUND));

        String jwtToken = jwtTokenService.generateAccessToken(user);
        String refreshToken = refreshTokenService.generateRefreshToken(user);

        Set<String> roles = new LinkedHashSet<>();
        for(Role role : user.getRoles()) {
            roles.add(role.getName());
        }

        LoginResponse response = new LoginResponse(
                user.getUsername(),
                roles,
                jwtToken,
                refreshToken
        );
        return ApiResponse.<LoginResponse>builder()
                .data(response)
                .message(ApiMessages.Success.LOGIN_SUCCESS)
                .build();
    }
}
