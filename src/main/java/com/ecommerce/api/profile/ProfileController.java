package com.ecommerce.api.profile;

import com.ecommerce.api.constructs.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController implements CrudController<ProfileModel, String> {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("")
    public ResponseEntity<ProfileModel> create(@RequestBody ProfileModel entity) {
        ProfileModel profile = this.profileService.create(entity);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("")
    public ResponseEntity<List<ProfileModel>> getAll() {
        List<ProfileModel> profiles = this.profileService.getAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileModel> getById(@PathVariable String id) {
        Optional<ProfileModel> profile = this.profileService.getById(id);
        return profile.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileModel> update(@PathVariable String id, @RequestBody ProfileModel entity) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
