package com.jinnme.service.client.chatgpt.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatGptCompletionResponseChoiceDto(
        @JsonProperty("finish_reason")
        String finishReason,
        long index,
        ChatGptCompletionResponseChoiceMessageDto message,
        ChatGptCompletionResponseChoiceLogprobesDto logprobs
) {
}
