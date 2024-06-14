package com.jinnme.event.response;

import com.jinnme.service.bot.dto.ResponseDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MessageResponseEvent extends ApplicationEvent {
    private final ResponseDto response;

    public MessageResponseEvent(ResponseDto response) {
        super(response);
        this.response = response;
    }
}
