package com.jinnme.service.registration;

import com.jinnme.model.Message;
import com.jinnme.model.Session;
import com.jinnme.model.User;
import com.jinnme.repository.MessageRepository;
import com.jinnme.service.bot.dto.ChatDto;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.bot.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageSaveServiceImplTest {
    @InjectMocks
    private MessageSaveServiceImpl messageRegistrationService;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private SessionFetchService sessionFetchService;
    @Mock
    private UserFetchService userFetchService;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    void saveMessageOnlyTest() {
        var messageDto = createMessageDto();
        var session = mock(Session.class);
        var user = mock(User.class);
        when(userFetchService.fetch(messageDto.from())).thenReturn(user);
        when(sessionFetchService.fetch(messageDto.chat())).thenReturn(session);

        messageRegistrationService.saveMessage(messageDto);

        verify(messageRepository).save(messageArgumentCaptor.capture());
        var message = messageArgumentCaptor.getValue();
        assertAll(
                () -> assertEquals(messageDto.text(), message.getText()),
                () -> assertEquals(user, message.getFrom()),
                () -> assertEquals(session, message.getSession())
        );
    }

    private MessageDto createMessageDto() {
        var userDto = mock(UserDto.class);
        var chatDto = mock(ChatDto.class);
        return new MessageDto(userDto, chatDto, "text");
    }
}