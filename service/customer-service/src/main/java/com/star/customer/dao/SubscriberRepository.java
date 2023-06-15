package com.star.customer.dao;

import com.star.customer.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Caolp
 */
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
