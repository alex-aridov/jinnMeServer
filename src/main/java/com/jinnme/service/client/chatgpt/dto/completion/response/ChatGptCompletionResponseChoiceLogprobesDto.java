package com.jinnme.service.client.chatgpt.dto.completion.response;

import java.util.List;

public record ChatGptCompletionResponseChoiceLogprobesDto(
        List<ChatGptCompletionResponseChoiceLogProbesContentDto> content
) {
}
