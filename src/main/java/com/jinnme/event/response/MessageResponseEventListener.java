package com.jinnme.event.response;

import com.jinnme.service.bot.telegram.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageResponseEventListener implements ApplicationListener<MessageResponseEvent>  {
    private final TelegramBot bot;

    @Override
    public void onApplicationEvent(MessageResponseEvent event) {
        bot.sendMessage(event.getResponse());
    }
}
