package com.ecommerce.api.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    private ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    private ResponseEntity<UserModel> getUser(@PathVariable String id){
        Optional<UserModel> user = this.userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    private ResponseEntity<UserModel> createUser(@Valid @RequestBody UserInputDto user){
        UserModel createdUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
