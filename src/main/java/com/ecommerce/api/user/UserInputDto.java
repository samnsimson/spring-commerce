package com.ecommerce.api.user;

import lombok.Data;

@Data
public class UserInputDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
