package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinnme.model.ChatGptMessageRole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChatGptCompletionRequestMessageDto(
        String content,
        String name,
        ChatGptMessageRole role
) {
}
