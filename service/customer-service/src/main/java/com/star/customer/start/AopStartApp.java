package com.star.customer.start;

import com.star.customer.aop.AopServiceWithException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Caolp
 */
public class AopStartApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml");
        AopServiceWithException aopServiceWithException = context.getBean("aopServiceWithException", AopServiceWithException.class);
        aopServiceWithException.doMethod1();
        aopServiceWithException.doMethod2();
        try {
            aopServiceWithException.doMethod3();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
