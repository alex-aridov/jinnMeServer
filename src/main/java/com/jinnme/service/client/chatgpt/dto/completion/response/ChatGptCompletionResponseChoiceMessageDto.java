package com.jinnme.service.client.chatgpt.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jinnme.model.ChatGptMessageRole;

import java.util.List;

public record ChatGptCompletionResponseChoiceMessageDto(
        String content,
        ChatGptMessageRole role,
        @JsonProperty("tool_calls")
        List<ChatGptCompletionResponseChoiceMessageTollcallDto> toolCalls
) {
}
