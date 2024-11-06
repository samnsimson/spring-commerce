package com.ecommerce.api.auth;

import com.ecommerce.api.user.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@Valid  @RequestBody LoginInputDto entity) throws Exception {
        LoginResponseDto response = this.authService.login(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    private ResponseEntity<UserModel> signup(@Valid @RequestBody SignupInputDto entity) throws Exception {
        UserModel user = this.authService.signup(entity);
        return ResponseEntity.ok(user);
    }
}
