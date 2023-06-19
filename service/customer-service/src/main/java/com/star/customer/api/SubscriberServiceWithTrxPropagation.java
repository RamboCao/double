package com.star.customer.api;

import com.star.customer.dao.SubscriberRepository;
import com.star.customer.domain.Subscriber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Caolp
 */
@Service
public class SubscriberServiceWithTrxPropagation implements ISubscriberServiceWithTrxPropagation{

    @Resource
    private SubscriberRepository subscriberRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequired(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequiredException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNewException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNestedException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatory(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatoryException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupports(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupportsException(Subscriber subscriber){
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNever(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNeverException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void addNotSupported(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void addNotSupportedException(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        throw new RuntimeException();
    }
}
