package com.jinnme.event.response;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageResponseEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(MessageResponseEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
