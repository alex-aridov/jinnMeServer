package com.jinnme.service.bot.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinnme.service.bot.dto.ResponseDto;
import com.jinnme.service.bot.mapper.MessageMapper;
import com.jinnme.event.request.MessageRequestEvent;
import com.jinnme.event.request.MessageRequestEventPublisher;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot implements ChatBot, SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final MessageMapper messageMapper;
    private final MessageRequestEventPublisher messageRequestEventPublisher;

    @Getter
    @Value("${telegram.token}")
    private final String botToken;

    private TelegramClient telegramClient;

    @PostConstruct
    public void init() {
        telegramClient = new OkHttpTelegramClient(getBotToken());
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            log.info("Update {}", objectMapper.writeValueAsString(update));
        } catch (JsonProcessingException e) {
            log.error("cant serialize update {}", update, e);
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageDto = messageMapper.from(update.getMessage());
            messageRequestEventPublisher.publishEvent(new MessageRequestEvent(messageDto));
        }
    }

    @Override
    public void sendMessage(ResponseDto responseDto) {
        SendMessage message = SendMessage
                .builder()
                .chatId(responseDto.chatId())
                .text(responseDto.text())
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("failed to send message {}", responseDto, e);
        }
    }

    @SuppressWarnings("unused")
    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        log.info("Registered bot running state is: {}", botSession.isRunning());
    }
}
