package com.jinnme.service.registration;

import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestMessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseChoiceMessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    UUID saveMessage(MessageDto messageDto);
    void saveMessage(UUID sessionId, ChatGptCompletionResponseChoiceMessageDto messageDto);
    List<ChatGptCompletionRequestMessageDto> getMessages(UUID sessionId);
}
