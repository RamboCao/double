package com.star.openai.domain.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author caolp
 */
@Getter
@Setter
@ToString
public class ChatResult<T> implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long code;

    private String status;

    private List<T> data;
}
