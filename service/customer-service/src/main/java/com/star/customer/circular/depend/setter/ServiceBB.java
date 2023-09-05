package com.star.customer.circular.depend.setter;


public class ServiceBB {
    private ServiceAA serviceAA;

    public ServiceBB(){
        System.out.println("serviceBB 创建成功");

    }

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
    }

    public ServiceAA getServiceAA() {
        return serviceAA;
    }
}
