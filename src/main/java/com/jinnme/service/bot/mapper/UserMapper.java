package com.jinnme.service.bot.mapper;

import com.jinnme.service.bot.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper
public interface UserMapper {
    @Mapping(target = "platformId", source = "id")
    UserDto from(User user);
}
