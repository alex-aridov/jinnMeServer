package com.jinnme.service.client.chatgpt.dto.completion.response;

public record ChatGptCompletionResponseChoiceMessageTollcallDto(
        String id,
        String type,
        ChatGptCompletionResponseChoiceMessageTollcallFunctionDto function
) {
}
