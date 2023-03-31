package com.star.openai.domain.chat;

import com.plexpt.chatgpt.entity.chat.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author caolp
 */
@Getter
@Setter
@ToString
public class ChatRequest {

    private List<Message> message;

}
