package com.jinnme.service.client.chatgpt;

import com.jinnme.event.response.MessageResponseEvent;
import com.jinnme.event.response.MessageResponseEventPublisher;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.bot.dto.ResponseDto;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestDto;
import com.jinnme.service.registration.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {
    private final ChatGptClient chatGptClient;
    private final MessageService messageService;
    private final MessageResponseEventPublisher messageResponseEventPublisher;

    @Override
    public void handle(MessageDto messageDto) {
        var sessionId = messageService.saveMessage(messageDto);
        var messages = messageService.getMessages(sessionId);
        var request = ChatGptCompletionRequestDto.builder()
                .model(ChatGptModel.GPT_3_5_TURBO)
                .user(messageDto.from().platformId())
                .messages(messages)
                .build();
        var response = chatGptClient.getChatCompletion(request);
        var chatGptMessage = response.choices().get(0).message();
        messageService.saveMessage(sessionId, chatGptMessage);

        var responseDto = new ResponseDto(chatGptMessage.content(), messageDto.chat().platformId());
        messageResponseEventPublisher.publishEvent(new MessageResponseEvent(responseDto));
    }
}
