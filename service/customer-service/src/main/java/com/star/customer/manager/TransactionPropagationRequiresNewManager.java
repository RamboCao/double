package com.star.customer.manager;

import com.star.customer.api.ICustomerServiceWithTrxPropagation;
import com.star.customer.api.ISubscriberServiceWithTrxPropagation;
import com.star.customer.domain.Customer;
import com.star.customer.domain.Status;
import com.star.customer.domain.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * @author Caolp
 */
@Component
@Slf4j
public class TransactionPropagationRequiresNewManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围方法没有事务，客户和用户的插入都是再自己的事务中独立运行，外围方法抛出异常回滚不会影响内部方法
     * customer、subscriber 正常插入
     */
    public void noTransactionExceptionRequiresNewRequiresNew(){
        customerServiceWithTrxPropagation.addRequiresNew(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiresNew(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法没有开启事务，插入客户和用户会分别开启自己的事务，插入用户时抛出异常回滚，其它事务不受影响
     * customer 插入成功，subscriber 插入失败
     */
    public void noTransactionRequiresNewRequiresNewException(){
        customerServiceWithTrxPropagation.addRequiresNew(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiresNewException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法开启事务
     * Jerry 传播类型未 Require 与外围方法同属于一个事务，当外围方法抛出异常，那么 Jerry 会插入失败
     * 而 Tim 和 用户方法是单独的新建事务中，外围方法抛出异常不会受到影响
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionRequiresNewRequireNew(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Jerry", 2L, Instant.now(), 2L, Instant.now(), Status.VALID));
        customerServiceWithTrxPropagation.addRequiresNew(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiresNew(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * Jerry 未插入，Time 插入成功，用户 0123456789 插入失败
     * 外围方法开启事务，插入 Jerry 和外围方法一个事务
     * 插入 Tim 和用户 0123456789 分别再独立的新事务中
     * 插入用户 0123456789 方法抛出异常，首先插入用户的方法会回滚，继续抛出异常被外围方法感知，外围方法事务同样被回滚, 所以 Jerry 的插入也会被回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredRequiresNewRequiresNewException(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Jerry", 2L, Instant.now(), 2L, Instant.now(), Status.VALID));
        customerServiceWithTrxPropagation.addRequiresNew(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiresNewException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法开启事务，Jerry 和外围方法一个事务，Tim 和 用户分别在独立新的事务中
     * Jerry 和 Tim 插入成功， 0123456789 插入失败
     * 插入用户 0123456789 抛出异常，首先插入用户方法回滚，异常被 catch 不会被外围方法感知，外围事务不会回滚，所以 Jerry 会插入成功
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredRequiresNewRequiresNewExceptionTry(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Jerry", 2L, Instant.now(), 2L, Instant.now(), Status.VALID));
        customerServiceWithTrxPropagation.addRequiresNew(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        try {
            subscriberServiceWithTrxPropagation.addRequiresNewException(new Subscriber("0123456789"));
        }catch (Exception e){
            log.error("0123456789 失败回滚");
        }
    }
}
