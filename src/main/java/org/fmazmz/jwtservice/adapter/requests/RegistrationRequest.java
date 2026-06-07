package org.fmazmz.jwtservice.adapter.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank
        @Size(min = 5, message = "Username must be at least 5 characters.")
        String username,

        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters.")
        String password
) {
}
