package com.jinnme.repository;

import com.jinnme.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByPlatformId(String platformId);
}
