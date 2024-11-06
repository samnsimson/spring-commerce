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

    @PostMapping("")
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserInputDto entity) {
        UserModel createdUser = this.userService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("")
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserModel> users = this.userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserModel> getById(@PathVariable String id) {
        Optional<UserModel> user = this.userService.getById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserModel> update(@PathVariable String id, @RequestBody UserModel entity) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return this.userService.delete(id);
    }
}
