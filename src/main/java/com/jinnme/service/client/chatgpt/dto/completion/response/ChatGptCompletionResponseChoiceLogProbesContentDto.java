package com.jinnme.service.client.chatgpt.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatGptCompletionResponseChoiceLogProbesContentDto(
                String token,
                Double logprob,
                byte[] bytes,
                @JsonProperty("top_logprobs")
                List<ChatGptCompletionResponseChoiceLogProbesContentDto> topLogprobes
) {
}
