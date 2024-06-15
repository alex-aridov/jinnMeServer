package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChatGptCompletionRequestStreamOptionsDto(
        @JsonProperty("include_usage")
        Boolean includeUsage
) {
}
