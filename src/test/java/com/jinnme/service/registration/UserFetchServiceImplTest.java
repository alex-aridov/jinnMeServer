package com.jinnme.service.registration;

import com.jinnme.model.User;
import com.jinnme.repository.UserRepository;
import com.jinnme.service.bot.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFetchServiceImplTest {
    @InjectMocks
    private UserFetchServiceImpl userFetchService;
    @Mock
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    void fetchNewTest() {
        var userDto = createUserDto();
        when(userRepository.findByPlatformId(userDto.platformId())).thenReturn(Optional.empty());

        userFetchService.fetch(userDto);

        verify(userRepository).save(userArgumentCaptor.capture());
        var user = userArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals(userDto.platformId(), user.getPlatformId()),
                () -> assertEquals(userDto.userName(), user.getUserName()),
                () -> assertEquals(userDto.firstName(), user.getFirstName()),
                () -> assertEquals(userDto.lastName(), user.getLastName()),
                () -> assertEquals(userDto.languageCode(), user.getLanguageCode()),
                () -> assertEquals(userDto.isBot(), user.getIsBot())
        );
    }

    @Test
    void fetchExistsTest() {
        var userDto = createUserDto();
        when(userRepository.findByPlatformId(userDto.platformId())).thenReturn(Optional.of(new User()));

        userFetchService.fetch(userDto);

        verify(userRepository).findByPlatformId(userDto.platformId());
        verifyNoMoreInteractions(userRepository);
    }

    private UserDto createUserDto() {
        return new UserDto("platformId",
                "userName",
                "firstName",
                "lastName",
                false,
                "en");
    }
}