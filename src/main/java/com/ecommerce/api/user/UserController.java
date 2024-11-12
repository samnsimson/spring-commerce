package com.ecommerce.api.user;

import com.ecommerce.api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ApiResponse<UserModel> create(@Valid @RequestBody UserInputDto entity) {
        UserModel createdUser = this.userService.create(entity);
        return new ApiResponse<>(HttpStatus.CREATED, createdUser);
    }

    @GetMapping("")
    public ApiResponse<List<UserModel>> getAll() {
        List<UserModel> users = this.userService.getAll();
        return new ApiResponse<>(HttpStatus.OK, users);
    }

    @GetMapping("{id}")
    public ApiResponse<UserModel> getById(@PathVariable String id) {
        Optional<UserModel> user = this.userService.getById(id);
        if(user.isEmpty()) return new ApiResponse<>(HttpStatus.OK,null);
        UserModel targetUser = user.get();
        return new ApiResponse<>(HttpStatus.OK, targetUser);
    }

    @PutMapping("{id}")
    public ApiResponse<UserModel> update(@PathVariable String id, @RequestBody UserInputDto entity) {
        UserModel updatedUser = this.userService.update(id, entity);
        return new ApiResponse<>(HttpStatus.OK, updatedUser);
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        return new ApiResponse<>(HttpStatus.OK,"Deleted");
    }
}
