package com.star.order.config.annotation;

import java.lang.annotation.*;

import static com.star.order.config.DataSourceNames.MASTER;

/**
 * @author Caolp
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingDataSource {

    String routeKey() default MASTER;
}
