package com.jinnme.event.request;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRequestEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(MessageRequestEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
