package com.star.customer.dao;

import com.star.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Caused by: java.lang.IllegalArgumentException: Modifying queries can only use void or int/Integer as return type!
     * Offending method: public abstract com.star.customer.domain.Customer com.star.customer.dao.CustomerRepository.updateStatus
     * (java.lang.Long,com.star.customer.domain.Status)
     */
    @Modifying
    @Query(value = "update double.customer set status = 1 where id = :customerId", nativeQuery = true)
    void updateStatus(Long customerId);
}
