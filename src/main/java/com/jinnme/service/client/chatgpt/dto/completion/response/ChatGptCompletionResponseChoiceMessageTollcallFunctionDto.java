package com.jinnme.service.client.chatgpt.dto.completion.response;

public record ChatGptCompletionResponseChoiceMessageTollcallFunctionDto(
        String name,
        String arguments
) {
}
