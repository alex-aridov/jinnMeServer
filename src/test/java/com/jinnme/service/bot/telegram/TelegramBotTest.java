package com.jinnme.service.bot.telegram;

import com.jinnme.event.request.MessageRequestEvent;
import com.jinnme.event.request.MessageRequestEventPublisher;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.bot.dto.ResponseDto;
import com.jinnme.service.bot.mapper.MessageMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramBotTest {
    @InjectMocks
    private TelegramBot telegramBot;
    @Mock
    private MessageMapper messageMapper;
    @Mock
    private MessageRequestEventPublisher messageRequestEventPublisher;

    @Captor
    private ArgumentCaptor<MessageRequestEvent> messageRequestEventArgumentCaptor;
    @Captor
    private ArgumentCaptor<SendMessage> sendMessageArgumentCaptor;

    @Test
    void consumeTextMessageTest() {
        var update = mock(Update.class);
        var message = mock(Message.class);

        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);

        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("Hello World");

        var messageDto = mock(MessageDto.class);
        when(messageMapper.from(message)).thenReturn(messageDto);

        telegramBot.consume(update);

        verify(messageRequestEventPublisher).publishEvent(messageRequestEventArgumentCaptor.capture());
        assertEquals(messageDto, messageRequestEventArgumentCaptor.getValue().getMessage());
    }

    @Test
    void consumeNonTextMessageTest() {
        var update = mock(Update.class);
        var message = mock(Message.class);

        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);

        when(message.hasText()).thenReturn(false);

        telegramBot.consume(update);

        verifyNoInteractions(messageRequestEventPublisher);
    }

    @Test
    void consumeNonMessageTest() {
        var update = mock(Update.class);
        when(update.hasMessage()).thenReturn(false);

        telegramBot.consume(update);

        verifyNoInteractions(messageRequestEventPublisher);
    }

    @Test
    void sendMessageTest() throws TelegramApiException {
        var telegramClient = mock(TelegramClient.class);

        var responseDto = new ResponseDto("text", "chatId");
        ReflectionTestUtils.setField(telegramBot, "telegramClient", telegramClient);
        telegramBot.sendMessage(responseDto);

        verify(telegramClient).execute(sendMessageArgumentCaptor.capture());
        var sendMessage = sendMessageArgumentCaptor.getValue();
        assertEquals(responseDto.chatId(), sendMessage.getChatId());
        assertEquals(responseDto.text(), sendMessage.getText());
    }
}