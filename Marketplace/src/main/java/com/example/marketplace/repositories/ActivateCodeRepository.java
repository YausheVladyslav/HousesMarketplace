package com.example.marketplace.repositories;

import com.example.marketplace.entities.ActivationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivateCodeRepository extends JpaRepository<ActivationCodeEntity, Long> {
    Optional<ActivationCodeEntity> findByUserEmail(String email);
}
