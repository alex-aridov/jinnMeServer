package com.jinnme.service.client.chatgpt;

import com.jinnme.service.client.chatgpt.dto.completion.request.ChatGptCompletionRequestDto;
import com.jinnme.service.client.chatgpt.dto.completion.response.ChatGptCompletionResponseDto;

public interface ChatGptClient {
    ChatGptCompletionResponseDto getChatCompletion(ChatGptCompletionRequestDto request);
}
