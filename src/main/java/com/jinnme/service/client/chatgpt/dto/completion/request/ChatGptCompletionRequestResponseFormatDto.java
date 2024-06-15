package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChatGptCompletionRequestResponseFormatDto(ChatGptCompletionRequestResponseFormat type) {
}
