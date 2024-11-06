package com.ecommerce.api.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginInputDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 & 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 3 & 50 characters")
    private String password;
}
