package com.star.openai.api.chat;

import com.star.openai.domain.chat.ChatRequest;
import com.star.openai.domain.chat.ChatResult;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author caolp
 */
@Path("chat-completion")
public interface IChatCompletionService {
    /**
     * 输入内容进行请求
     * @param request => message
     * @return ChatResult -> data
     */
    @POST
    @Path("/chat")
    ChatResult<String> chatCompletion(ChatRequest request);
}
