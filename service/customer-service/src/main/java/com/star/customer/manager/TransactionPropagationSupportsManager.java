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
public class TransactionPropagationSupportsManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围没有事务，那么内层方法将以非事务的方式运行
     * 外围方法抛出异常，不影响内层方法
     * 所以客户插入成功并且用户插入成功
     */
    public void noTransactionExceptionSupportsSupports(){
        customerServiceWithTrxPropagation.addSupports(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addSupports(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围没有事务，那么内层方法将以非事务的方式运行
     * 外围方法抛出异常，不影响内层方法
     * 所以客户插入成功并且用户插入成功
     * 即使用户插入抛出异常，由于是非事务，所以还是会插入成功
     */
    public void noTransactionSupportsSupportsException(){
        customerServiceWithTrxPropagation.addSupports(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addSupportsException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法存在事务，那么内层方法也会使用外层方法事务
     * 外围方法抛出异常，所有内层方法都会回滚
     * 用户和客户保存都失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionSupportsSupports(){
        customerServiceWithTrxPropagation.addSupports(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addSupports(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法存在事务，那么内层方法也会使用外层方法事务
     * 外围方法抛出异常，所有内层方法都会回滚
     * 用户和客户保存都失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionSupportsSupportsException(){
        customerServiceWithTrxPropagation.addSupports(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addSupportsException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法存在事务，那么内层方法也会使用外层方法事务
     * 外围方法抛出异常，所有内层方法都会回滚
     * 用户和客户保存都失败
     * 即使异常被捕获，还是会被外层 customer 保存感知，所以还是会回滚
     * Transaction silently rolled back because it has been marked as rollback-only
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionSupportsSupportsExceptionTry(){
        customerServiceWithTrxPropagation.addSupports(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        try {
            subscriberServiceWithTrxPropagation.addSupportsException(new Subscriber("0123456789"));
        }catch (Exception e){
            log.error("用户插入失败, 事务回滚");
        }
    }
}
