package com.star.openai.manager.chat;


import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author caolp
 */
public class TestFunction {
    public static void main(String[] args) {
        String key = "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";
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(600)
                .apiKey(key)
//                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build()
                .init();


        ConsoleStreamListener listener = new ConsoleStreamListener();
        Message message = Message.of("SQL SERVER 表备份命令与过程描述");
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .messages(Collections.singletonList(message))
                .build();
        chatGPTStream.streamChatCompletion(chatCompletion, listener);
    }
}
