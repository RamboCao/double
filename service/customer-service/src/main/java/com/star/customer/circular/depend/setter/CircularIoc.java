package com.star.customer.circular.depend.setter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Caolp
 */
public class CircularIoc {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("circularTest.xml");
        ServiceAA serviceAA = context.getBean("serviceAA", ServiceAA.class);
        ServiceBB serviceBB = context.getBean("serviceBB", ServiceBB.class);
    }

}
