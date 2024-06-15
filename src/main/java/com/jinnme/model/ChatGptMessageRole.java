package com.jinnme.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatGptMessageRole {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    TOOL("tool");
    private final String code;

    @JsonValue
    public String getCode() {
        return code;
    }
}
