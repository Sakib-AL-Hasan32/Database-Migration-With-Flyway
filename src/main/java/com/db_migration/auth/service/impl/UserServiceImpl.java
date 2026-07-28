package com.db_migration.auth.service.impl;

import com.db_migration.auth.dto.request.RegisterRequest;
import com.db_migration.auth.dto.response.RegisterResponse;
import com.db_migration.auth.entity.User;
import com.db_migration.auth.repository.UserRepository;
import com.db_migration.auth.service.UserService;
import com.db_migration.common.constants.ApiMessages;
import com.db_migration.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<RegisterResponse> register(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.username())) {
            throw new IllegalArgumentException(ApiMessages.Error.USER_ALREADY_EXISTS);
        }
        if(userRepository.existsByEmail(registerRequest.email())) {
            throw new IllegalArgumentException(ApiMessages.Error.EMAIL_ALREADY_EXISTS);
        }
        User user = User.builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
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
}
