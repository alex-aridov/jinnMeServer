package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum ChatGptCompletionRequestResponseFormat {
    TEXT("text"),
    JSON("json_object");
    private final String code;

    @Override
    public String toString() {
        return code;
    }
}
