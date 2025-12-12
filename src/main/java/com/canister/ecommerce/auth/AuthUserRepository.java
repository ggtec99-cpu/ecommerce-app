package com.canister.ecommerce.auth;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserModel, UUID> {
    Optional<AuthUserModel> findByUsername(String username);
    Optional<AuthUserModel> findByEmail(String email);
}
