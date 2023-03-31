package com.star.openai.api.chat;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.SseStreamListener;
import com.star.openai.OpenAiConfig;
import com.star.openai.domain.chat.ChatRequest;
import com.star.openai.domain.chat.ChatResult;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author caolp
 */
@Service
public class ChatCompletionServiceImpl implements IChatCompletionService {

    @Override
    public ChatResult<String> chatCompletion(ChatRequest request) {
        ChatGPTStream chatGptStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(OpenAiConfig.getInstance().getOpenAiKey())
                //.proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();
        SseStreamListener sseStreamListener = new SseStreamListener(new SseEmitter());
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(List.of(Message.of(request.getMessage())))
                .build();
        chatGptStream.streamChatCompletion(chatCompletion, sseStreamListener);
        return new ChatResult<>();
    }
}
