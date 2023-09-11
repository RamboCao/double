package com.star.customer.aop;

/**
 * @author Caolp
 */

public class AopServiceWithException{

    public void doMethod1(){
        System.out.println("CustomerService.doMethod1()");
    }

    public String doMethod2(){
        System.out.println("CustomerService.doMethod2()");
        return "hello world";
    }

    public String doMethod3() throws Exception {
        System.out.println("CustomerService.doMethod3()");
        throw new Exception("some exception");
    }
}
