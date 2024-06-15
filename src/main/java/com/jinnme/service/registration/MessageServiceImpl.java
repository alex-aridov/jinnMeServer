package com.jinnme.service.registration;

import com.jinnme.model.ChatGptMessageRole;
import com.jinnme.model.Message;
import com.jinnme.repository.MessageRepository;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestMapper;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestMessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseChoiceMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final UserFetchService userFetchService;
    private final SessionFetchService sessionFetchService;
    private final MessageRepository messageRepository;
    private final ChatGptCompletionRequestMapper chatGptCompletionRequestMapper;

    @Override
    @Transactional
    public UUID saveMessage(MessageDto messageDto) {
        var session = sessionFetchService.fetch(messageDto.chat());
        var user = userFetchService.fetch(messageDto.from());

        Message message = new Message();
        message.setText(messageDto.text());
        message.setFrom(user);
        message.setSession(session);
        message.setRole(ChatGptMessageRole.USER);

        messageRepository.save(message);
        return session.getId();
    }

    @Override
    @Transactional
    public void saveMessage(UUID sessionId, ChatGptCompletionResponseChoiceMessageDto messageDto) {
        var session = sessionFetchService.fetch(sessionId);

        Message message = new Message();
        message.setText(messageDto.content());
        message.setSession(session);
        message.setRole(messageDto.role());

        messageRepository.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatGptCompletionRequestMessageDto> getMessages(UUID sessionId) {
        return messageRepository.getMessagesBySessionIdOrderByCreatedAt(sessionId).stream()
                .map(chatGptCompletionRequestMapper::from)
                .toList();
    }
}
