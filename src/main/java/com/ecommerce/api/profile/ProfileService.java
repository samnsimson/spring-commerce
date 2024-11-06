package com.ecommerce.api.profile;

import com.ecommerce.api.constructs.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements CrudService<ProfileModel, String> {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileModel create(ProfileModel entity) {
        return this.profileRepository.save(entity);
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
