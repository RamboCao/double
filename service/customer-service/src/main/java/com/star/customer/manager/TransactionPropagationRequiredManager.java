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
public class TransactionPropagationRequiredManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围方法 noTransactionExceptionRequiredRequired 没有事务
     * 两个保存没有异常，外围方法抛出异常
     * 结果，两个数据正常插入，外围方法的异常不影响内部插入两条数据方法独立的事务
     */
    public void noTransactionExceptionRequiredRequired(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequired(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法 noTransactionRequiredRequiredException 没有事务
     * 客户保存正常，用户保存抛出异常，外围方法没有异常
     * 结果，客户插入正常，用户插入失败
     * 二者都在自己独立的事务中运行，用户插入的失败不影响客户的插入
     */
    public void noTransactionRequiredRequiredException(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiredException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也回滚
     * 客户，用户均未正常插入
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionRequiredRequired(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequired(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常，外围方法感知异常导致整个事务回滚
     * 客户、用户均未正常插入
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredRequiredException(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addRequiredException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被 catch 不被外围方法感知，整个事务都会回滚
     * 客户、用户均未插入
     * Transaction silently rolled back because it has been marked as rollback-only
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredRequiredExceptionTry(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        try {
            subscriberServiceWithTrxPropagation.addRequiredException(new Subscriber("0123456789"));
        }catch (Exception e){
            log.error("方法回滚");
        }
    }
}
