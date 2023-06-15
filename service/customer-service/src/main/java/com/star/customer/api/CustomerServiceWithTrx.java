package com.star.customer.api;

import com.star.common.exception.BusinessException;
import com.star.customer.dao.CustomerRepository;
import com.star.customer.domain.Customer;
import com.star.customer.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author Caolp
 */
@Service
@Slf4j
public class CustomerServiceWithTrx implements ICustomerServiceWithTrx{

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private IMultiThreadTrxService multiThreadTrxService;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void publicMethod(Long id) {
        customerRepository.updateStatus(id);
        throw new RuntimeException("something went wrong");
    }

    @Override
    public void innerInvokeMethodTrx(){
        Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        System.out.println(jerry.toString());
        publicMethod(jerry.getId());
    }

    @Transactional
    public void multiThreadTrx(){
        Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        System.out.println(jerry.toString());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            multiThreadTrxService.multiThreadTrx();
        }).start();
    }

    /**
     * 错误的传播属性设置
     */
    @Transactional(propagation = Propagation.NEVER)
    public void saveWithWrongPropagation(){
        Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        System.out.println(jerry.toString());
        throw new RuntimeException("something went wrong");
    }

    /**
     * 自己将异常吞掉
     * 只需要在 catch 中再次抛出 RuntimeException 就可以
     */
    @Transactional
    public void saveWithWrongCatchException(){
        try {
            Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
            System.out.println(jerry.toString());
            throw new RuntimeException("something went wrong");
        }catch (Exception e){
            log.error("自己捕获异常, 并没有抛出");
        }
    }

    /**
     * 捕获了异常，但是又抛出了其它的异常，非 RuntimeException 和 Error
     * @throws Exception
     */
    @Transactional
    public void saveWithThrowOtherException() throws Exception {
        try {
            Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
            System.out.println(jerry.toString());
            throw new RuntimeException("something went wrong");
        }catch (Exception e){
            log.error("自己捕获异常, 并没有抛出");
            throw new Exception("抛出了一个 Exception 异常");
        }
    }

    /**
     * 自定义回滚异常
     * @throws Exception
     */
    @Transactional(rollbackFor = {BusinessException.class, RuntimeException.class})
    public void saveWithCustomizePropagationException() throws Exception {
        Customer jerry = customerRepository.save(new Customer("jerry", 1L, Instant.now(), 1L, Instant.now(), Status.VALID));
        System.out.println(jerry.toString());
        throw new IOException("something went wrong");
    }

}
