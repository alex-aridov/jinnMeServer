package com.jinnme.service.bot.telegram;

import com.jinnme.service.bot.dto.ResponseDto;

public interface ChatBot {
    void sendMessage(ResponseDto responseDto);
}
