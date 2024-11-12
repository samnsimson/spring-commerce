package com.ecommerce.api.user;

import com.ecommerce.api.mappers.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserModel create(UserInputDto entity) {
        boolean emailExists = this.userRepository.existsByEmail(entity.getEmail());
        boolean phoneExists = this.userRepository.existsByPhone(entity.getPhone());
        String errorMessage = "User with this email or phone already exists";
        if ( emailExists || phoneExists) throw new IllegalArgumentException(errorMessage);

        UserModel newUser = this.userMapper.mapFrom(entity);
        newUser.setRole("USER");
        newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
        return this.userRepository.save(newUser);
    }

    public List<UserModel> getAll() {
        return this.userRepository.findAll();
    }

    public Optional<UserModel> getById(String id) {
        return this.userRepository.findById(id);
    }

    public UserModel update(String id, UserInputDto entity) {
        boolean userExists = this.userRepository.existsById(id);
        if(!userExists) throw new UsernameNotFoundException("User not found");
        UserModel user = this.userMapper.mapFrom(entity);
        user.setId(id);
        return this.userRepository.save(user);
    }

    public boolean delete(String id) {
        this.userRepository.deleteById(id);
        return true;
    }

    public Optional<UserModel> getByEmailOrPhone(String email, String phone){
        return this.userRepository.findByEmailOrPhone(email, phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findByEmail(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found");

        UserModel currentUser = user.get();
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("READ"),
                new SimpleGrantedAuthority("WRITE")
        );

        return new CustomUserDetails(currentUser.getId(), currentUser.getEmail(), currentUser.getPassword(), authorities);
    }
}
