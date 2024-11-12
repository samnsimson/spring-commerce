package com.ecommerce.api.profile;

import com.ecommerce.api.mappers.ProfileMapper;
import com.ecommerce.api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("")
    public ApiResponse<ProfileModel> create(@Valid  @RequestBody ProfileInputDto entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getClaim("id");
        ProfileModel profile = this.profileService.create(entity, userId);
        return new ApiResponse<>(HttpStatus.CREATED, profile);
    }

    @GetMapping("")
    public ApiResponse<List<ProfileModel>> getAll() {
        List<ProfileModel> profiles = this.profileService.getAll();
        return new ApiResponse<>(HttpStatus.OK, profiles);
    }

    @GetMapping("{id}")
    public ApiResponse<ProfileModel> getById(@PathVariable String id) {
        ProfileModel profile = this.profileService.getById(id);
        return new ApiResponse<>(HttpStatus.OK, profile);
    }

    @PutMapping("{id}")
    public ApiResponse<ProfileModel> update(@PathVariable String id, @RequestBody ProfileInputDto entity) {
        ProfileModel profile = this.profileService.update(id, entity);
        return new ApiResponse<>(HttpStatus.OK, profile);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
