package com.star.customer.manager;

import com.star.customer.dao.CustomerRepository;
import com.star.customer.dao.SubscriberRepository;
import com.star.customer.domain.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Caolp
 */
@Component
public class TransactionTemplateManager {

    @Autowired
    private CustomerRepository customerRepository;

    @Resource
    private SubscriberRepository subscriberRepository;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    DataSourceProperties dataSourceProperties;

    public void platformTransactionManager(){
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSourceProperties.getUsername());
        System.out.println(dataSourceProperties.getPassword());

        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            subscriberRepository.save(new Subscriber("0123456789"));
            platformTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);
        }
    }

}
