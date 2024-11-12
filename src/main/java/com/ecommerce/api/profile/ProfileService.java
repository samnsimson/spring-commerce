package com.ecommerce.api.profile;

import com.ecommerce.api.mappers.ProfileMapper;
import com.ecommerce.api.user.UserModel;
import com.ecommerce.api.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(UserRepository userRepository, ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public ProfileModel create(ProfileInputDto entity, String userId) {
        Optional<UserModel> user = this.userRepository.findById(userId);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found!");
        UserModel currentUser = user.get();
        ProfileModel profile = this.profileMapper.mapFrom(entity);
        profile.setUser(currentUser);
        return this.profileRepository.save(profile);
    }

    public List<ProfileModel> getAll() {
        return this.profileRepository.findAll();
    }

    public Optional<ProfileModel> getById(String id) {
        return this.profileRepository.findById(id);
    }

    public ProfileModel update(String s, ProfileModel entity) {
        return null;
    }

    public ResponseEntity<Void> delete(String id) {
        this.profileRepository.deleteById(id);
        return null;
    }
}
