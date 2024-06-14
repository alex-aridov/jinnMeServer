package com.jinnme.service.registration;

import com.jinnme.service.bot.dto.MessageDto;

public interface MessageSaveService {
    void saveMessage(MessageDto messageDto);
}
