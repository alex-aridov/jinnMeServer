package com.jinnme.service.registration;

import com.jinnme.model.Chat;
import com.jinnme.model.Session;
import com.jinnme.model.SessionStatus;
import com.jinnme.repository.SessionRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionFetchServiceImplTest {
    @InjectMocks
    private SessionFetchServiceImpl sessionFetchService;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private ChatFetchService chatFetchService;
    @Captor
    private ArgumentCaptor<Session> sessionArgumentCaptor;

    @Test
    void fetchNewTest() {
        var chatDto = createChatDto();
        var chat = mock(Chat.class);
        when(chatFetchService.fetch(chatDto)).thenReturn(chat);
        when(sessionRepository.findByChatAndStatus(chat, SessionStatus.ACTIVE)).thenReturn(Optional.empty());

        sessionFetchService.fetch(chatDto);

        verify(sessionRepository).save(sessionArgumentCaptor.capture());
        var session = sessionArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals(SessionStatus.ACTIVE, session.getStatus()),
                () -> assertEquals(chat, session.getChat())
        );
    }

    @Test
    void fetchExistsTest() {
        var chatDto = createChatDto();
        var chat = mock(Chat.class);
        when(chatFetchService.fetch(chatDto)).thenReturn(chat);
        when(sessionRepository.findByChatAndStatus(chat, SessionStatus.ACTIVE)).thenReturn(Optional.of(new Session()));

        sessionFetchService.fetch(chatDto);

        verify(sessionRepository).findByChatAndStatus(chat, SessionStatus.ACTIVE);
        verifyNoMoreInteractions(sessionRepository);
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