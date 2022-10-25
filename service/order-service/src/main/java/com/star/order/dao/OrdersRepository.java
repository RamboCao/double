package com.star.order.dao;

import com.star.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Caolp
 */
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCodeAndCustomerId(String code, Long customerId);

    Orders findByCode(String code);

}
