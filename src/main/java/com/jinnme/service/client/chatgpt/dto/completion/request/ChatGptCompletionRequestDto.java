package com.jinnme.service.client.chatgpt.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jinnme.service.client.chatgpt.ChatGptModel;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatGptCompletionRequestDto {
        private List<ChatGptCompletionRequestMessageDto> messages;
        private ChatGptModel model;
        @JsonProperty("frequency_penalty")
        private Double frequencyPenalty;
        @JsonProperty("logit_bias")
        private Map<String, String> logitBias;
        private Boolean logprobs;
        @JsonProperty("top_logprobs")
        private Integer topLogprobs;
        @JsonProperty("max_tokens")
        private Integer maxTokens;
        @JsonProperty("n")
        private Integer maxChoiceCount;
        @JsonProperty("presence_penalty")
        private Double presencePenalty;
        @JsonProperty("response_format")
        private ChatGptCompletionRequestResponseFormatDto responseFormat;
        private Integer seed;
        private Collection<String> stop;
        private Boolean stream;
        @JsonProperty("stream_options")
        private ChatGptCompletionRequestStreamOptionsDto streamOptions;
        private Double temperature;
        @JsonProperty("top_p")
        private Double topP;
        private Collection<ChatGptCompletionRequestToolDto> tools;
        @JsonProperty("tool_choice")
        private String toolChoice;
        @JsonProperty("parallel_tool_calls")
        private Boolean parallelToolCalls;
        private String user;
}
