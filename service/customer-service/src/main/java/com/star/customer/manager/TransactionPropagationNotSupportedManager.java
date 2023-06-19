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
import java.sql.Time;
import java.time.Instant;
import java.util.Objects;

/**
 * @author Caolp
 */
@Component
public class TransactionPropagationNotSupportedManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 客户和用户都正常插入
     * 外围方法没有事务，required 单独事务不会受到外围方法异常的影响
     * 而用户插入当前没有事务，所以也能正常插入
     */
    public void noTransactionExceptionRequiredNotSupported(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNotSupported(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 客户和用户都正常插入
     * 外围方法没有事务，required 单独事务不受用户插入异常的影响
     * 而用户插入当前没有事务，即使抛出异常，也能正常插入
     */
    public void noTransactionRequiredNotSupportedException(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNotSupportedException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法存在事务, 客户插入单独事务，但是外围方法抛出异常
     * 感知外围方法异常，客户插入失败回滚，而用户插入会将当前事务挂起
     * 以非事务的方式运行，所以用户插入成功
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionRequiredNotSupported(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNotSupported(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法存在事务, 客户插入单独事务，但是用户插入抛出异常
     * 感知用户插入抛出的异常，客户插入失败回滚，而用户插入会将当前事务挂起
     * 以非事务的方式运行，所以用户插入成功
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredNotSupportedException(){
        customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNotSupportedException(new Subscriber("0123456789"));
    }

    /**
     * 由于 not supported 以非事务的方式运行，并且将当前事务挂起，那么它看不到存在的事务中的数据
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionRequiredNotSupportedGet(){
        Customer tim = customerServiceWithTrxPropagation.addRequired(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        Customer customerByIdNotSupported = customerServiceWithTrxPropagation.getCustomerByIdNotSupported(tim.getId());
        if (Objects.nonNull(customerByIdNotSupported)){
            System.out.println("getRequired可见");
        }
        Customer customerById = customerServiceWithTrxPropagation.getCustomerById(tim.getId());
        if(Objects.nonNull(customerById)){
            System.out.println("get可见");
        }
    }


}
