package com.jinnme.service.client.chatgpt;

import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseDto;
import com.jinnme.utils.ObjectToShortJson;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatGptClientImpl implements ChatGptClient {
    @Value("${chatGpt.token}")
    private final String token;
    private final ObjectToShortJson objectToShortJson;
    private static final String URL = "https://api.openai.com/v1";
    private static final String CHAT_COMPLETION = "/chat/completions";

    private RestClient restClient;

    @PostConstruct
    private void init () {
        restClient = RestClient.builder()
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    @Override
    public ChatGptCompletionResponseDto getChatCompletion(ChatGptCompletionRequestDto request) {
        log.info("Chat completion request {}", objectToShortJson.getJson(request));
        var response = restClient.post()
                .uri(URL + CHAT_COMPLETION)
                .body(request)
                .retrieve()
                .body(ChatGptCompletionResponseDto.class);
        log.info("Chat completion response {}", objectToShortJson.getJson(response));
        return response;
    }
}
