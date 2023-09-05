package com.star.customer.circular.depend.setter;

/**
 * @author Caolp
 */
public class ServiceAA {

    private ServiceBB serviceBB;

    public ServiceAA(){
        System.out.println("serviceAA 创建成功");
    }

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
    }

    public ServiceBB getServiceBB() {
        return serviceBB;
    }
}
