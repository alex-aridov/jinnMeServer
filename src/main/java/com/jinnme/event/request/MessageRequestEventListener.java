package com.jinnme.event.request;

import com.jinnme.service.client.chatgpt.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageRequestEventListener implements ApplicationListener<MessageRequestEvent>  {
    private final ChatGptService chatGptService;

    @Override
    public void onApplicationEvent(MessageRequestEvent event) {
        chatGptService.handle(event.getMessage());
    }
}
