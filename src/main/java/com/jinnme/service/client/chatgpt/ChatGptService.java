package com.jinnme.service.client.chatgpt;

import com.jinnme.service.bot.dto.MessageDto;

public interface ChatGptService {
    void handle(MessageDto messageDto);
}
