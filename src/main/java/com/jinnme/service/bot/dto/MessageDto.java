package com.jinnme.service.bot.dto;

public record MessageDto(
        UserDto from,
        ChatDto chat,
        String text
) {
}
