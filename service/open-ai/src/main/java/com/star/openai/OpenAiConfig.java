package com.star.openai;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
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
public class OpenAiConfig implements ApplicationContextAware, InitializingBean {

    private static ApplicationContext applicationContext;

    public static OpenAiConfig getInstance(){
        return getBean("openAiConfig", OpenAiConfig.class);
    }

    static final String OPEN_AI_PREFIX = "double.open.ai";

    @NotBlank
    private String openAiKey = "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";

    public static <T> T getBean(String beanName, Class<T> clazz){
        return applicationContext.getBeansOfType(clazz).get(beanName);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("open.ai.config.is " + toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.setApplicationContext(applicationContext);
    }

}
