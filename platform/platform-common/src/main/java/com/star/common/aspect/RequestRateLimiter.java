package com.star.common.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
@Getter
@Setter
@Builder
public class RequestRateLimiter {

    private RateLimiter rateLimiter;

    private long timeout;

    private TimeUnit timeUnit;

    public boolean tryAcquire(){
        return rateLimiter.tryAcquire(timeout, timeUnit);
    }

    public boolean tryAcquire(int permits){
        return rateLimiter.tryAcquire(permits, timeout, timeUnit);
    }

}
