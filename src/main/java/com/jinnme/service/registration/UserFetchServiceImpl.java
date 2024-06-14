package com.jinnme.service.registration;

import com.jinnme.model.User;
import com.jinnme.repository.UserRepository;
import com.jinnme.service.bot.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFetchServiceImpl implements UserFetchService {
    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public User fetch(UserDto userDto) {
        return userRepository.findByPlatformId(userDto.platformId())
                .orElseGet(() -> {
                    User user = new User();
                    user.setUserName(userDto.userName());
                    user.setFirstName(userDto.firstName());
                    user.setLastName(userDto.lastName());
                    user.setIsBot(userDto.isBot());
                    user.setPlatformId(userDto.platformId());
                    user.setLanguageCode(userDto.languageCode());
                    return userRepository.save(user);
                });
    }
}
