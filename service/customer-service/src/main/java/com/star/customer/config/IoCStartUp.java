package com.star.customer.config;

import com.star.customer.domain.Subscriber;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Caolp
 */
public class IoCStartUp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("subscriber.xml");
        System.out.println(context);
        Subscriber subscriber = context.getBean(Subscriber.class);
        System.out.println(subscriber);

    }
}
