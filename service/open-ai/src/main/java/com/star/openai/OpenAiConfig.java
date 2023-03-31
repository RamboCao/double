package com.star.openai;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caolp
 */
@Component
@RefreshScope
@Validated
@Getter
@Setter
@ToString
@Slf4j
@ConfigurationProperties(prefix = OpenAiConfig.OPEN_AI_PREFIX, ignoreInvalidFields = false)
public class OpenAiConfig implements SmartInitializingSingleton {
    static final String OPEN_AI_PREFIX = "double.open.ai";

    private List<String> openAiKey = new ArrayList<>(){};

    @Override
    public void afterSingletonsInstantiated() {
        log.info("open.ai.config.is " + toString());
    }

}
