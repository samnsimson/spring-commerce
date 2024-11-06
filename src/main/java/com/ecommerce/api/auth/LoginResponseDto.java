package com.ecommerce.api.auth;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String message;
    private String token;
    private String user;
}
