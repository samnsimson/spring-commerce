package com.ecommerce.api.auth;

import com.ecommerce.api.jwt.JwtService;
import com.ecommerce.api.user.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginInputDto entity) {
        try {
            String username = entity.getUsername();
            String password = entity.getPassword();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication = this.authenticationManager.authenticate(authToken);
            String token = this.jwtService.generateToken(authentication);
            LoginResponseDto response = new LoginResponseDto("Success",token,username);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            LoginResponseDto errorResponse = new LoginResponseDto("Failure",null,null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/signup")
    private ResponseEntity<UserModel> signup(@Valid @RequestBody SignupInputDto entity) throws Exception {
        UserModel user = this.authService.signup(entity);
        return ResponseEntity.ok(user);
    }
}
