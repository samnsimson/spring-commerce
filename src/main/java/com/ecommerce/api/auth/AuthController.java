package com.ecommerce.api.auth;

import com.ecommerce.api.user.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginInputDto entity) {
        try {
            LoginResponseDto response = this.authService.login(entity);
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
