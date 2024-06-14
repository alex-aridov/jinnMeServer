package com.jinnme.repository;

import com.jinnme.model.Message;
import com.jinnme.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> getMessagesBySessionOrderByCreatedAt(Session session);
}
