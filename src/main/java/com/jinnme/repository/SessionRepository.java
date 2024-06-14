package com.jinnme.repository;

import com.jinnme.model.Chat;
import com.jinnme.model.Session;
import com.jinnme.model.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByChatAndStatus(Chat chat, SessionStatus status);
}
