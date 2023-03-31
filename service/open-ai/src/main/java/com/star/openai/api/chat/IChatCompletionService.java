package com.star.openai.api.chat;

import com.star.openai.domain.chat.ChatRequest;
import com.star.openai.domain.chat.ChatResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author caolp
 */
@RequestMapping("chat-completion")
public interface IChatCompletionService {
    /**
     * 输入内容进行请求
     * @param request => message
     * @return ChatResult -> data
     */
    @PostMapping("/chat")
    @ResponseBody
    ChatResult<String> chatCompletion(ChatRequest request);
}
