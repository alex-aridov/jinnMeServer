package com.jinnme.service.echo;

import com.jinnme.service.bot.dto.MessageDto;

public interface EchoHandler {
    void handle(MessageDto messageDto);
}
