package com.star.customer.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }


    public void doBefore(){
        System.out.println("前置通知");
    }

    public void doAfterReturning(String result){
        System.out.println("后置通知, 返回值: " + result);
    }

    public void doAfterThrowing(Exception e){
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    public void doAfter(){
        System.out.println("最终通知");

    }
}
