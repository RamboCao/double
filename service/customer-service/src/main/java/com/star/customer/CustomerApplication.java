package com.star.customer;

import com.star.common.restful.JerseyServiceAutoScanner;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 * @author Caolp
 */
@SpringBootApplication(scanBasePackages = "com.star")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    /**
     * 直接 resourceConfig.register(ICustomerService.class) && 去掉 ApplicationPath("api")
     * 请求地址为 GET http://localhost:9005/customer/find-by-id/1 可行
     *
     * 在 CustomerApplication 中 加上 @ApplicationPath("/api") 请求地址为 GET http://localhost:9005/api/customer/find-by-id/1 也不行, 为啥添加注解不行
     *
     * 配置文件中添加   servlet:context-path: /api 请求地址为 GET http://localhost:9005/api/customer/find-by-id/1 可行 ContextPath must start with '/' and not end with '/'
     *
     * 配置文件中添加 spring.jersey:application-path: api 请求地址为 GET http://localhost:9005/api/customer/find-by-id/1 可行
     *
     * 换成这种注册方式就不行了 resourceConfig.register(JerseyServiceAutoScanner.getPublishJerseyServiceClasses(applicationContext, "com.star.customer.api"));
     *
     * 添加 spring.jersey:application-path: ${spring.application.name} 请求地址为 GET http://localhost:9005/customer-service/customer/find-by-id/1 resourceConfig.register(ICustomerService.class); 可行
     *
     * 之前注册应该错了，应该使用的是 resourceConfig.registerClasses(), 使用这个然后再用 GET http://localhost:9005/customer-service/customer/find-by-id/1 这个请求就可以了
     */
    @Bean
    public ResourceConfig jerseyConfig(ApplicationContext applicationContext){
        ResourceConfig resourceConfig = new ResourceConfig();
        // 这个地方可以进行依赖传递， customer-service 依赖 platform-data-access 而 platform-data-access 依赖 platform-common, 所以能直接使用 common 下的东西
        resourceConfig.registerClasses(JerseyServiceAutoScanner.getPublishJerseyServiceClasses(applicationContext, "com.star.customer.api"));
        return  resourceConfig;
    }
}
