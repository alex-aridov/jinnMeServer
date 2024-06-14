package com.jinnme.service.registration;

import com.jinnme.model.User;
import com.jinnme.service.bot.dto.UserDto;

public interface UserFetchService {
    User fetch(UserDto userDto);
}
