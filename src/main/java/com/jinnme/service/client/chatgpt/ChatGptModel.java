package com.jinnme.service.client.chatgpt;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatGptModel {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4_O("gpt-4o");

    private final String code;

    @JsonValue
    public String getCode() {
        return code;
    }
}
