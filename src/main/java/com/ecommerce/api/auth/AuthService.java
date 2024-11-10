package com.ecommerce.api.auth;

import com.ecommerce.api.jwt.JwtService;
import com.ecommerce.api.user.UserInputDto;
import com.ecommerce.api.user.UserModel;
import com.ecommerce.api.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthService(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginInputDto entity){
        String username = entity.getUsername();
        String password = entity.getPassword();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(authToken);
        String token = this.jwtService.generateToken(authentication);
        return new LoginResponseDto("Success", token, username);
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
