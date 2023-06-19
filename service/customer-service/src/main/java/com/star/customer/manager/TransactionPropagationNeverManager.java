package com.star.customer.manager;

import com.star.customer.api.ICustomerServiceWithTrxPropagation;
import com.star.customer.api.ISubscriberServiceWithTrxPropagation;
import com.star.customer.domain.Customer;
import com.star.customer.domain.Status;
import com.star.customer.domain.Subscriber;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;

/**
 * @author Caolp
 */
@Component
public class TransactionPropagationNeverManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围方法没有事务
     * 内层方法以非事务方式执行
     * 即使外层方法抛出异常并且被感知，那么还是会插入数据成功
     */
    public void noTransactionExceptionNeverNever(){
        customerServiceWithTrxPropagation.addNever(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNever(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务
     * 内层方法以非事务方式执行
     * 即使内层方法抛出异常并且被感知，那么还是会插入数据成功
     */
    public void noTransactionNeverNeverException(){
        customerServiceWithTrxPropagation.addNever(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNeverException(new Subscriber("0123456789"));
    }

    /**
     * 抛出异常 Existing transaction found for transaction marked with propagation 'never'
     * 用户和客户全部插入失败
     * 以非事务的方法执行，当前存在事务，那么所有的事务全部回滚
     * 另外两种方法不再执行，情况一致
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionNeverNever(){
        customerServiceWithTrxPropagation.addNever(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNever(new Subscriber("0123456789"));
        throw new RuntimeException();
    }
}
