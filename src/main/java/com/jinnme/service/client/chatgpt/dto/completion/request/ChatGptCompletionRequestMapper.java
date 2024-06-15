package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.jinnme.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChatGptCompletionRequestMapper {
    @Mapping(target = "content", source = "text")
    @Mapping(target = "name", source = "message.from.platformId")
    ChatGptCompletionRequestMessageDto from(Message message);
}
