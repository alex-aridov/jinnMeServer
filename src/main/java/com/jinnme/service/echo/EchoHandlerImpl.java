package com.jinnme.service.echo;

import com.jinnme.event.response.MessageResponseEvent;
import com.jinnme.event.response.MessageResponseEventPublisher;
import com.jinnme.service.bot.dto.MessageDto;
import com.jinnme.service.bot.dto.ResponseDto;
import com.jinnme.service.registration.MessageSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EchoHandlerImpl implements EchoHandler {
    private final MessageSaveService messageSaveService;
    private final MessageResponseEventPublisher messageResponseEventPublisher;

    @Override
    public void handle(MessageDto messageDto) {
        messageSaveService.saveMessage(messageDto);
        var responseDto = new ResponseDto(messageDto.text(), messageDto.chat().platformId());
        messageResponseEventPublisher.publishEvent(new MessageResponseEvent(responseDto));
    }

}
