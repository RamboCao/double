package com.star.common.aspect;


import com.google.common.util.concurrent.RateLimiter;
import com.star.common.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Order(1)
@Aspect
public class RateLimiterAspect {

    private static final ConcurrentHashMap<String, RequestRateLimiter> RATE_LIMITER_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.star.common.annotation.RateLimit)")
    public void pointCut() {

    }

    @Around(value = "pointCut()", argNames = "pjp")
    @SuppressWarnings("all")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String className = signature.getClass().getName();
        String methodName = method.getName();
        String rateLimiterName = className.concat(".").concat(methodName);
        RateLimit rateLimit = AnnotationUtils.findAnnotation(method, RateLimit.class);
        RequestRateLimiter requestRateLimiter = null;
        if (rateLimit != null) {
            if (RATE_LIMITER_CONCURRENT_HASH_MAP.containsKey(rateLimiterName)){
                requestRateLimiter = RATE_LIMITER_CONCURRENT_HASH_MAP.get(rateLimiterName);
            }else {
                synchronized (rateLimiterName){
                    RateLimiter rateLimiter = RateLimiter.create(rateLimit.permitsPerSecond(), rateLimit.timeOut(), rateLimit.timeUnit());
                    requestRateLimiter = RequestRateLimiter.builder()
                            .rateLimiter(rateLimiter)
                            .timeout(rateLimit.timeOut())
                            .timeUnit(rateLimit.timeUnit())
                            .build();
                    RATE_LIMITER_CONCURRENT_HASH_MAP.putIfAbsent(rateLimiterName, requestRateLimiter);
                }
            }
        }
        if(requestRateLimiter.tryAcquire()){
            return pjp.proceed(pjp.getArgs());
        }
        throw new RuntimeException("接口请求过于频繁, 稍后再试");
    }

}
