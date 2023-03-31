package com.star.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author caolp
 */
@SpringBootApplication(scanBasePackages = "com.star")
public class OpenAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenAiApplication.class, args);
    }
}
