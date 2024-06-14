package com.jinnme.service.registration;

import com.jinnme.model.Chat;
import com.jinnme.repository.ChatRepository;
import com.jinnme.service.bot.dto.ChatDto;
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
class ChatFetchServiceImplTest {
    @InjectMocks
    private ChatFetchServiceImpl chatFetchService;
    @Mock
    private ChatRepository chatRepository;
    @Captor
    private ArgumentCaptor<Chat> chatArgumentCaptor;

    @Test
    void fetchNewTest() {
        var chatDto = createChatDto();
        when(chatRepository.findByPlatformId(chatDto.platformId())).thenReturn(Optional.empty());

        chatFetchService.fetch(chatDto);

        verify(chatRepository).save(chatArgumentCaptor.capture());
        var chat = chatArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals(chatDto.platformId(), chat.getPlatformId()),
                () -> assertEquals(chatDto.firstName(), chat.getFirstName()),
                () -> assertEquals(chatDto.lastName(), chat.getLastName()),
                () -> assertEquals(chatDto.type(), chat.getType()),
                () -> assertEquals(chatDto.title(), chat.getTitle()),
                () -> assertEquals(chatDto.userName(), chat.getUserName())
        );
    }

    @Test
    void fetchExistsTest() {
        var chatDto = createChatDto();
        when(chatRepository.findByPlatformId(chatDto.platformId())).thenReturn(Optional.of(new Chat()));

        chatFetchService.fetch(chatDto);

        verify(chatRepository).findByPlatformId(chatDto.platformId());
        verifyNoMoreInteractions(chatRepository);
    }

    private ChatDto createChatDto() {
        return new ChatDto("platformId",
                "type",
                "title",
                "firstName",
                "lastName",
                "userName");
    }
}