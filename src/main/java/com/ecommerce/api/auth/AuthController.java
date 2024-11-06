package com.ecommerce.api.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    private ResponseEntity<Void> login(@Valid  @RequestBody LoginInputDto entity){
        return null;
    }

    @PostMapping("/signup")
    private ResponseEntity<Void> signup(@Valid @RequestBody SignupInputDto entity){
        return null;
    }
}
