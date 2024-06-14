package com.jinnme.service.bot.mapper;

import com.jinnme.service.bot.dto.ChatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;

@Mapper
public interface ChatMapper {
    @Mapping(target = "platformId", source = "id")
    ChatDto from (Chat user);
}
