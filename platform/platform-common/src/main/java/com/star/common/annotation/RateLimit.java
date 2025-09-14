package com.star.common.annotation;


import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(value = ElementType.METHOD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * 限流器名称
     * @return 限流器名称
     */
    String name() default "";

    /**
     * 每秒处理多少请求
     * @return 每秒处理多少请求
     */
    @NotBlank
    long permitsPerSecond() default 100;

    /**
     * 超时时间
     * @return 超时时间
     */
    long timeOut() default 0;

    /**
     * 单位
     * @return 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
