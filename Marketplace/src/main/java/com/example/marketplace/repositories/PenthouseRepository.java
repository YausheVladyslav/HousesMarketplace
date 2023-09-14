package com.example.marketplace.repositories;

import com.example.marketplace.entities.penthouse.PenthouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenthouseRepository extends JpaRepository<PenthouseEntity, Long> {

    Page<PenthouseEntity> findByTitleContainingIgnoreCase(Pageable pageable, String announcementName);
}
