package com.jinnme.service.registration;

import com.jinnme.model.Chat;
import com.jinnme.service.bot.dto.ChatDto;

public interface ChatFetchService {
    Chat fetch(ChatDto chatDto);
}
