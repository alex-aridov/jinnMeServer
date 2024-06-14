package com.jinnme.event.request;

import com.jinnme.service.bot.dto.MessageDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MessageRequestEvent extends ApplicationEvent {
    private final MessageDto message;

    public MessageRequestEvent(MessageDto message) {
        super(message);
        this.message = message;
    }
}
