package com.jinnme.service.registration;

import com.jinnme.model.Session;
import com.jinnme.model.SessionStatus;
import com.jinnme.repository.SessionRepository;
import com.jinnme.service.bot.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionFetchServiceImpl implements SessionFetchService {
    private final ChatFetchService chatFetchService;
    private final SessionRepository sessionRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Session fetch(ChatDto chatDto) {
        var chat = chatFetchService.fetch(chatDto);
        return sessionRepository.findByChatAndStatus(chat, SessionStatus.ACTIVE)
                .orElseGet(() -> {
                    Session session = new Session();
                    session.setChat(chat);
                    return sessionRepository.save(session);
                });
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Session fetch(UUID sessionId) {
        return sessionRepository.getReferenceById(sessionId);
    }
}
