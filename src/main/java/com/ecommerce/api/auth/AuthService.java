package com.ecommerce.api.auth;

import com.ecommerce.api.user.UserInputDto;
import com.ecommerce.api.user.UserModel;
import com.ecommerce.api.user.UserService;
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

    public LoginResponseDto login(LoginInputDto entity) throws Exception {
        Optional<UserModel> user = this.userService.getByEmailOrPhone(entity.getUsername(), entity.getUsername());
        if(user.isEmpty()) throw new Exception("Email or phone does not exists");
        UserModel existingUser = user.get();
        boolean passwordMatches = this.passwordEncoder.matches(entity.getPassword(), existingUser.getPassword());
        if(!passwordMatches) throw new Exception("Wrong password");

        LoginResponseDto response = new LoginResponseDto();
        response.setMessage("Success");
        response.setToken("Token");
        response.setUser("User");
        return response;
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
