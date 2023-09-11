package com.star.customer.start;


import com.star.customer.life.cycle.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Caolp
 */
@Slf4j
public class BeanLifeCycleStartApp {
    public static void main(String[] args) {
        log.info("Init application context");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.star.customer.life.cycle");
        User user = (User) context.getBean("user");
        log.info(user.toString());
        log.info("shutdown application context");
        context.registerShutdownHook();

    }
}
