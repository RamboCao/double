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
public class TransactionPropagationMandatoryManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围方法没有事务
     * 客户和用户数据都插入失败
     * No existing transaction found for transaction marked with propagation 'mandatory'
     * 需要外围方法有事务才可以
     */
    public void noTransactionExceptionMandatoryMandatory(){
        customerServiceWithTrxPropagation.addMandatory(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addMandatory(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务
     * 客户和用户数据都插入失败
     * No existing transaction found for transaction marked with propagation 'mandatory'
     * 需要外围方法有事务才可以
     */
    public void noTransactionMandatoryMandatoryException(){
        customerServiceWithTrxPropagation.addMandatory(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addMandatoryException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法存在事务
     * 客户和用户都插入失败
     * 内层方法使用外围方法的事务，外围方法抛出事务，内层方法感知，并且进行回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionMandatoryMandatory(){
        customerServiceWithTrxPropagation.addMandatory(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addMandatory(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法存在事务
     * 客户和用户都插入失败
     * 内层方法使用外围方法的事务，客户方法抛出异常，抛出，然后所有方法全部回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionMandatoryMandatoryException(){
        customerServiceWithTrxPropagation.addMandatory(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addMandatoryException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法存在事务
     * 客户和用户都插入失败
     * 内层方法使用外围方法的事务，客户方法抛出异常，抛出，然后所有方法全部回滚
     * 和 Required 一致，只要有方法异常就全部回滚，侧重点在于没有事务就会抛出异常
     * 即使异常被 catch 依然会被感知，所以还是会被回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionMandatoryMandatoryExceptionTry(){
        customerServiceWithTrxPropagation.addMandatory(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        try {
            subscriberServiceWithTrxPropagation.addMandatoryException(new Subscriber("0123456789"));
        }catch (Exception e){
            log.error("用户插入失败回滚");
        }
    }

}
