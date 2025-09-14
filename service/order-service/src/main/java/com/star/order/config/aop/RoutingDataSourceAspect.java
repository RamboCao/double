package com.star.order.config.aop;

import com.star.order.config.DynamicDataSource;
import com.star.order.config.annotation.RoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Caolp
 */
@Order(1)
@Aspect
@Slf4j
@Component
public class RoutingDataSourceAspect {

    @Pointcut("@annotation(com.star.order.config.annotation.RoutingDataSource)")
    public void routingDataSourcePointCut(){

    }

    @Around("routingDataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        RoutingDataSource datasource = method.getAnnotation(RoutingDataSource.class);
        DynamicDataSource.setDataSource(datasource.routeKey());
        System.out.println("当前数据源" + datasource.routeKey());
        log.debug("set database is " + datasource.routeKey());
        try {
            return point.proceed();
        }finally {
            DynamicDataSource.clearDataSource();;
            log.debug("clear datasource");
        }
    }

}
