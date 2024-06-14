package com.jinnme.event.request;

import com.jinnme.service.echo.EchoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRequestEventListener implements ApplicationListener<MessageRequestEvent>  {
    private final EchoHandler echoHandler;

    @Override
    public void onApplicationEvent(MessageRequestEvent event) {
        echoHandler.handle(event.getMessage());
    }
}
