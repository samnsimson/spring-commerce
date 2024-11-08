package com.ecommerce.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel create(UserInputDto entity) {
        boolean emailExists = this.userRepository.existsByEmail(entity.getEmail());
        boolean phoneExists = this.userRepository.existsByPhone(entity.getPhone());
        String errorMessage = "User with this email or phone already exists";
        if ( emailExists || phoneExists) throw new IllegalArgumentException(errorMessage);

        UserModel newUser = new UserModel();
        newUser.setFirstName(entity.getFirstName());
        newUser.setLastName(entity.getLastName());
        newUser.setEmail(entity.getEmail());
        newUser.setPhone(entity.getPhone());
        newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
        return this.userRepository.save(newUser);
    }

    public List<UserModel> getAll() {
        return this.userRepository.findAll();
    }

    public Optional<UserModel> getById(String id) {
        return this.userRepository.findById(id);
    }

    public UserModel update(String s, UserModel entity) {
        return null;
    }

    public ResponseEntity<Void> delete(String id) {
        this.userRepository.deleteById(id);
        return null;
    }

    public Optional<UserModel> getByEmailOrPhone(String email, String phone){
        return this.userRepository.findByEmailOrPhone(email, phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findByEmail(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found");
        UserModel currentUser = user.get();
        return User.withUsername(currentUser.getEmail()).password(currentUser.getPassword()).authorities("read").build();
    }
}
