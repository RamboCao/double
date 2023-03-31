package com.star.openai.api.chat;

import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.star.openai.domain.chat.ChatRequest;
import com.star.openai.domain.chat.ChatResult;
import org.springframework.stereotype.Service;

/**
 * @author caolp
 */
@Service
public class ChatCompletionServiceImpl implements IChatCompletionService {
    @Override
    public ChatResult<String> chatCompletion(ChatRequest request) {
        return null;
    }
}
