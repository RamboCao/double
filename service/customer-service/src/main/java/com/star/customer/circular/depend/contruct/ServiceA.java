package com.star.customer.circular.depend.contruct;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB){
         this.serviceB = serviceB;
    }

}
