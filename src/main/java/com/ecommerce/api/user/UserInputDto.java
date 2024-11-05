package com.ecommerce.api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInputDto {
    @NotBlank(message = "Please provide a valid firstName")
    @Size(min = 3, max = 50, message = "firstName must be between 3 & 50 characters")
    private String firstName;

    @NotBlank(message = "Please provide a valid firstName")
    @Size(min = 3, max = 50, message = "firstName must be between 3 & 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;
}
