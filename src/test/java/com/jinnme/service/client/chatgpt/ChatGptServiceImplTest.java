package com.jinnme.service.client.chatgpt;

import com.jinnme.event.response.MessageResponseEvent;
import com.jinnme.event.response.MessageResponseEventPublisher;
import com.jinnme.model.ChatGptMessageRole;
import com.jinnme.service.bot.dto.ChatDto;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.bot.dto.UserDto;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestDto;
import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestMessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseChoiceDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseChoiceMessageDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseUsageDto;
import com.jinnme.service.registration.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatGptServiceImplTest {
    @InjectMocks
    private ChatGptServiceImpl chatGptService;
    @Mock
    private MessageService messageService;
    @Mock
    private ChatGptClient chatGptClient;
    @Mock
    private MessageResponseEventPublisher messageResponseEventPublisher;
    @Captor
    private ArgumentCaptor<ChatGptCompletionRequestDto> requestDtoArgumentCaptor;
    @Captor
    private ArgumentCaptor<MessageResponseEvent> messageResponseEventArgumentCaptor;

    private static final UUID SESSION_ID = UUID.randomUUID();

    @Test
    void handleTest() {
        var messageDto = createMessage();

        List<ChatGptCompletionRequestMessageDto> messages = List.of(mock(ChatGptCompletionRequestMessageDto.class));
        when(messageService.saveMessage(messageDto)).thenReturn(SESSION_ID);
        when(messageService.getMessages(SESSION_ID)).thenReturn(messages);

        var response = createResponse();
        when((chatGptClient.getChatCompletion(any(ChatGptCompletionRequestDto.class)))).thenReturn(response);

        chatGptService.handle(messageDto);

        verify(chatGptClient).getChatCompletion(requestDtoArgumentCaptor.capture());
        verify(messageService).saveMessage(SESSION_ID, response.choices().getFirst().message());
        verify(messageResponseEventPublisher).publishEvent(messageResponseEventArgumentCaptor.capture());

        var responseDto = messageResponseEventArgumentCaptor.getValue().getResponse();
        assertEquals(response.choices().getFirst().message().content(), responseDto.text());
        assertEquals(messageDto.chat().platformId(), responseDto.chatId());

        var requestDto = requestDtoArgumentCaptor.getValue();
        assertEquals(messageDto.from().platformId(), requestDto.getUser());
        assertEquals(messages, requestDto.getMessages());
    }

    private MessageDto createMessage() {
        var user = new UserDto("platformId",
                "userName",
                "firstName",
                "lastName",
                false,
                "languageCode");
        var chat = new ChatDto("platformId",
                "type",
                "title",
                "firstName",
                "lastName",
                "userName");
        return new MessageDto(user, chat, "text");
    }

    private ChatGptCompletionResponseDto createResponse() {
        var message = new ChatGptCompletionResponseChoiceMessageDto(
                "content",
                ChatGptMessageRole.ASSISTANT,
                List.of()
        );
        var choice = new ChatGptCompletionResponseChoiceDto(
                "finishReason",
                1,
                message,
                null
        );
        return new ChatGptCompletionResponseDto(
                "id",
                List.of(choice),
                123,
                "model",
                "fingerprint",
                "object",
                new ChatGptCompletionResponseUsageDto(1,2,3)
        );
    }
}