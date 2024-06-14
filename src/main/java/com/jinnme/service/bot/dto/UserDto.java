package com.jinnme.service.bot.dto;

public record UserDto(
        String platformId,
        String userName,
        String firstName,
        String lastName,
        Boolean isBot,
        String languageCode
) {
}
