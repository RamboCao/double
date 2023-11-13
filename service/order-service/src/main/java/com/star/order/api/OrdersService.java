package com.star.order.api;

import com.star.order.dao.OrdersRepository;
import com.star.order.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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

    /**
     * 这个方法主要用来测试 Repository 的事务，测试 JDBC connection 获取过程
     * 以及 findById() 和 save() 事务之间的管理，独立事务，互不影响，抛出异常的情况：抛出异常也不会回滚
     * 添加 @Transactional 注解之后事务的变化：变成同一个事务，同一个连接，抛出异常会进行回滚
     * @EnableJpaRepositories(enableDefaultTransactions = false) 注解添加对于事务的影响，没有事务了，会报错，必须
     * 添加 @Transactional 注解才能执行
     * @param id
     * @return
     */
    // @Transactional
    public Orders serviceMethodTransactionTest(Long id){
        Orders orders = ordersRepository.findById(id).orElse(null);
        orders.setCode("3456789");
        Orders orders1 = ordersRepository.save(orders);
        orders1.setModifyInstant(Instant.now());
        Orders orders2 = ordersRepository.saveAndFlush(orders1);
        int i = 1/0;
        return orders2;
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
