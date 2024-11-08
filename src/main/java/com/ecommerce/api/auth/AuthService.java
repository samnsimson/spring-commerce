package com.ecommerce.api.auth;

import com.ecommerce.api.user.UserInputDto;
import com.ecommerce.api.user.UserModel;
import com.ecommerce.api.user.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    public UserModel signup(SignupInputDto entity){
        UserInputDto user = new UserInputDto();
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPhone(entity.getPhone());
        user.setPassword(entity.getPassword());
        return this.userService.create(user);
    }
}
