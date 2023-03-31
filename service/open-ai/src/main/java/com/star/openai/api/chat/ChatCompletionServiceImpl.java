package com.star.openai.api.chat;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.star.openai.OpenAiConfig;
import com.star.openai.domain.chat.ChatRequest;
import com.star.openai.domain.chat.ChatResult;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author caolp
 */
@Service
public class ChatCompletionServiceImpl implements IChatCompletionService {

    static ChatGPTStream chatGPTStream = ChatGPTStream.builder()
            .timeout(600)
            .apiKey(OpenAiConfig.getInstance().getOpenAiKey())
            //.proxy(proxy)
            .apiHost("https://api.openai.com/")
            .build()
            .init();

    @Override
    public ChatResult<String> chatCompletion(ChatRequest request) {
        SseStreamListener sseStreamListener = new SseStreamListener(new SseEmitter());
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(request.getMessage())
                .build();
        chatGPTStream.streamChatCompletion(chatCompletion, sseStreamListener);
        return new ChatResult<>();
    }
}
