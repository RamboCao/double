package com.star.customer.life.cycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "user", initMethod = "doInit", destroyMethod = "doDestory")
    public User create(){
        User user = new User();
        user.setName("caolp");
        user.setAge(18);
        return user;
    }
}
