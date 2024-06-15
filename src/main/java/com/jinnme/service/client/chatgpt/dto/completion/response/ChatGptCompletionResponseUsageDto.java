package com.jinnme.service.client.chatgpt.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatGptCompletionResponseUsageDto(
        @JsonProperty("completion_tokens")
        long completionTokens,
        @JsonProperty("prompt_tokens")
        long promptTokens,
        @JsonProperty("total_tokens")
        long totalTokens
) {
}
