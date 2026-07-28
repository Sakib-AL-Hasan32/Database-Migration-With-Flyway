package com.db_migration.auth.dto.request;


import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "Username can not be blanked")
        String username,

        @NotBlank(message = "Email can not be blanked")
        String email,

        @NotBlank(message = "Password can not be blanked")
        String password,

        @NotBlank(message = "First Name can not be blanked")
        String firstName,

        @NotBlank(message = "Last Name can not be blanked")
        String lastName
) {
}
