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
public class TransactionPropagationNestedManager {

    @Resource
    private ISubscriberServiceWithTrxPropagation subscriberServiceWithTrxPropagation;

    @Resource
    private ICustomerServiceWithTrxPropagation customerServiceWithTrxPropagation;

    /**
     * 外围方法没有事务
     * 客户 Tim 和 用户 0123456789 正常插入
     * 由于外围方法没有事务，所以两个方法都在自己的事务中独立运行，外围方法异常不影响内部独立事务
     * 当外围方法未开启事务时，NESTED 和 REQUIRED 作用相同
     */
    public void noTransactionExceptionNestedNested(){
        customerServiceWithTrxPropagation.addNested(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNested(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法没有事务
     * 客户 Tim 插入正常， 0123456789 插入失败
     * 两个方法都在自己的事务中独立运行，所以用户插入失败之后只会回滚自己的事务，Tim 的插入不受影响
     */
    public void noTransactionNestedNestedException(){
        customerServiceWithTrxPropagation.addNested(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNestedException(new Subscriber("0123456789"));
    }

    // NestedTransactionNotSupportedException，不支持嵌套事务。Hibernate不支持嵌套事务。
    /**
     * 外围方法开启事务
     * 内部事务为外围方法的子事务
     * 外围方法回滚，内部方法也要回滚
     * Tim 插入失败，用户 0123456789 也插入失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionExceptionNestedNested(){
        customerServiceWithTrxPropagation.addNested(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNested(new Subscriber("0123456789"));
        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务
     * 内部方法时外围方法的子事务，内部方法抛出异常回滚
     * 外围方法感知异常致使整个事务整体回滚
     * Tim 插入失败，用户 0123456789 也插入失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionNestedNestedException(){
        customerServiceWithTrxPropagation.addNested(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        subscriberServiceWithTrxPropagation.addNestedException(new Subscriber("0123456789"));
    }

    /**
     * 外围方法开启事务
     * 内部方法时外围方法的子事务，内部方法抛出异常并捕获
     * Tim 插入成功，用户 0123456789 也插入失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionNestedNestedExceptionTry(){
        customerServiceWithTrxPropagation.addNested(new Customer("Tim", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        try {
            subscriberServiceWithTrxPropagation.addNestedException(new Subscriber("0123456789"));
        }catch (Exception e){
            log.error("0123456789 事务失败回滚");
        }
    }

}
