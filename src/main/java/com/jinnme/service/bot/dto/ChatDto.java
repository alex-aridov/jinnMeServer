package com.jinnme.service.bot.dto;

public record ChatDto(
        String platformId,
        String type,
        String title,
        String firstName,
        String lastName,
        String userName
) {
}
