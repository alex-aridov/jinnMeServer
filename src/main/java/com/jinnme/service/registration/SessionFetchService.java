package com.jinnme.service.registration;

import com.jinnme.model.Session;
import com.jinnme.service.bot.dto.ChatDto;

public interface SessionFetchService {
    Session fetch(ChatDto chatDto);
}
