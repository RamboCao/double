package com.star.customer.api;

import com.star.customer.dao.CustomerRepository;
import com.star.customer.domain.Customer;
import com.star.customer.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * @author Caolp
 */
@Service
@Slf4j
public class MultiThreadTrxService implements IMultiThreadTrxService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public void multiThreadTrx() {
        log.info("保存用户");
        Customer jerry = customerRepository.save(new Customer("Tom", 2L, Instant.now(), 2L, Instant.now(), Status.VALID));
        System.out.println(jerry.toString());
        log.info("保存用户成功");
        throw new RuntimeException("something went wrong");
    }
}
