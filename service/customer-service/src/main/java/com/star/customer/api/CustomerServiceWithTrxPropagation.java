package com.star.customer.api;

import com.star.customer.dao.CustomerRepository;
import com.star.customer.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Caolp
 */
@Service
public class CustomerServiceWithTrxPropagation implements ICustomerServiceWithTrxPropagation{

    @Resource
    private CustomerRepository customerRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequired(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested(Customer customer){
        customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatory(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupports(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNever(Customer customer) {
        customerRepository.save(customer);
    }

}
