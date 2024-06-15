package com.jinnme.service.client.chatgpt.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatGptCompletionResponseDto(
        String id,
        List<ChatGptCompletionResponseChoiceDto> choices,
        long created,
        String model,
        @JsonProperty("system_fingerprint")
        String systemFingerprint,
        String object,
        ChatGptCompletionResponseUsageDto usage
) {
}
