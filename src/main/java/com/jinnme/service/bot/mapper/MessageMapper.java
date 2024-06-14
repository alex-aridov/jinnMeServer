package com.jinnme.service.bot.mapper;

import com.jinnme.service.bot.dto.MessageDto;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Mapper(uses = {ChatMapper.class, UserMapper.class})
public interface MessageMapper {
    MessageDto from(Message message);
}
