package com.star.customer.aop.cjlib.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Caolp
 */
public class UserLogProxy implements MethodInterceptor {

    private Object target;

    public Object userLogProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("[before] execute method " + method.getName());
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("[after] execute method: " + method.getName() + ", return value " + result);
        return null;
    }
}
