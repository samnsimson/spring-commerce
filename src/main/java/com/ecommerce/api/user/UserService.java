package com.ecommerce.api.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getUsers(){
        return this.userRepository.findAll();
    }

    public Optional<UserModel> getUserById(String id){
        return this.userRepository.findById(id);
    }

    public Optional<UserModel> getUserByEmailOrPhone(String email, String phone){
        return this.userRepository.findByEmailOrPhone(email, phone);
    }

    public UserModel createUser(UserInputDto user){
        boolean emailExists = this.userRepository.existsByEmail(user.getEmail());
        boolean phoneExists = this.userRepository.existsByPhone(user.getPhone());
        if ( emailExists || phoneExists) throw new IllegalArgumentException("User with this email or phone already exists");

        UserModel newUser = new UserModel();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(newUser);
    }
}
