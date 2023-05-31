package com.star.order;

import com.star.common.restful.JerseyServiceAutoScanner;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Caolp
 */
@SpringBootApplication(scanBasePackages = "com.star")
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public ResourceConfig jerseyConfig(ApplicationContext applicationContext){
        ResourceConfig resourceConfig = new ResourceConfig();
        // 这个地方可以进行依赖传递， customer-service 依赖 platform-data-access 而 platform-data-access 依赖 platform-common, 所以能直接使用 common 下的东西
        resourceConfig.registerClasses(JerseyServiceAutoScanner.getPublishJerseyServiceClasses(applicationContext, "com.star.order.api"));
        return  resourceConfig;
    }
}
