package com.jinnme.service.registration;

import com.jinnme.model.Chat;
import com.jinnme.repository.ChatRepository;
import com.jinnme.service.bot.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChatFetchServiceImpl implements ChatFetchService {
    private final ChatRepository chatRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Chat fetch(ChatDto chatDto) {
        return chatRepository.findByPlatformId(chatDto.platformId())
                .orElseGet(() -> {
                    Chat newChat = new Chat();
                    newChat.setUserName(chatDto.userName());
                    newChat.setFirstName(chatDto.firstName());
                    newChat.setLastName(chatDto.lastName());
                    newChat.setPlatformId(chatDto.platformId());
                    newChat.setType(chatDto.type());
                    newChat.setTitle(chatDto.title());
                    return chatRepository.save(newChat);
                });
    }
}
