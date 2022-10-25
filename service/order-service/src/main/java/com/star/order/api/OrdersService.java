package com.star.order.api;

import com.star.order.dao.OrdersRepository;
import com.star.order.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Caolp
 */
@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    public List<Orders> findByCodeAndCustomerId(String code, Long customerId) {
        return ordersRepository.findByCodeAndCustomerId(code, customerId);
    }

    @Override
    public Orders findByCode(String code) {
        return ordersRepository.findByCode(code);
    }
}
