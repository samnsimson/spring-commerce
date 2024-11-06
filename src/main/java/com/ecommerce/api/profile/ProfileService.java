package com.ecommerce.api.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileModel create(ProfileInputDto entity) {
        ProfileModel profile = new ProfileModel();
        profile.setAddressOne(entity.getAddressOne());
        profile.setAddressTwo(entity.getAddressTwo());
        profile.setCity(entity.getCity());
        profile.setState(entity.getState());
        profile.setCountry(entity.getCountry());
        profile.setZipcode(entity.getZipcode());
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
