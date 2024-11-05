package com.ecommerce.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    Optional<UserModel> findByEmailOrPhone(String email, String phone);
}
