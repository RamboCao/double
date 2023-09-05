package com.star.customer.life.cycle;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Caolp
 */
@Slf4j
@ToString
public class User implements BeanFactoryAware, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {


    private String name;

    private int age;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    private String beanName;

    public User() {
        log.info("execute User#new User()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("execte BeanFactoryAware#setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        log.info("execute BeanNameAware#setName");
        this.beanName = beanName;
    }

    @Override
    public void destroy() throws Exception {
        log.info("execute DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("execute InitializingBean#afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("execute ApplicationContextAware#setApplicationContext");
        this.applicationContext = applicationContext;
    }

    public void setName(String name) {
        log.info("execute User#setName({})", name);
        this.name = name;
    }

    public void setAge(int age) {
        log.info("execute User#setAge({})", age);
        this.age = age;
    }

    public void doInit(){
        log.info("execute User#doInit");
    }

    public void doDestory(){
        log.info("execute User#doDestory");
    }
}
