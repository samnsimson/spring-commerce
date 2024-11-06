package com.ecommerce.api.auth;

import com.ecommerce.api.user.UserModel;
import com.ecommerce.api.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder= passwordEncoder;
    }

    private String login(LoginInputDto entity){
        Optional<UserModel> user = this.userService.getByEmailOrPhone(entity.getUsername(), entity.getUsername());
        if(user.isEmpty()) return "Wrong credentials";
        UserModel existingUser = user.get();
        boolean passwordMatches = this.passwordEncoder.matches(entity.getPassword(), existingUser.getPassword());
        if(!passwordMatches) return "Wrong password";
        return "success";
    }
}
