package com.jinnme.service.registration;

import com.jinnme.model.Message;
import com.jinnme.repository.MessageRepository;
import com.jinnme.service.bot.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MessageSaveServiceImpl implements MessageSaveService {
    private final UserFetchService userFetchService;
    private final SessionFetchService sessionFetchService;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public void saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setText(messageDto.text());
        message.setFrom(userFetchService.fetch(messageDto.from()));
        message.setSession(sessionFetchService.fetch(messageDto.chat()));
        messageRepository.save(message);
    }
}
