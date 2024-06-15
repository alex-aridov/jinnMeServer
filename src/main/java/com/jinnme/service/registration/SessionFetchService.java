package com.jinnme.service.registration;

import com.jinnme.model.Session;
import com.jinnme.service.bot.dto.ChatDto;

import java.util.UUID;

public interface SessionFetchService {
    Session fetch(ChatDto chatDto);
    Session fetch(UUID sessionId);
}
