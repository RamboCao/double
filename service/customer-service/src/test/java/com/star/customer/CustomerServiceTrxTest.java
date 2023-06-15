package com.star.customer;

import com.star.customer.api.CustomerServiceWithTrx;
import com.star.customer.api.IMultiThreadTrxService;
import com.star.customer.manager.TransactionPropagationRequiredManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTrxTest {
    @Autowired
    private CustomerServiceWithTrx serviceWithTrx;

    @Autowired
    private IMultiThreadTrxService multiThreadTrxService;

    @Autowired
    private TransactionPropagationRequiredManager transactionPropagationRequiredManager;

    @Test
    public void testPrivateMethod() throws Exception {
        serviceWithTrx.saveWithCustomizePropagationException();
    }


    @Test
    public void testMultiThreadTrxService(){
        multiThreadTrxService.multiThreadTrx();
    }


    /**
     * 外围未开启事务，
     */
    @Test
    public void testNoTransactionExceptionRequiredRequired(){
        transactionPropagationRequiredManager.noTransactionExceptionRequiredRequired();
    }

    @Test
    public void testNoTransactionExceptionRequiredRequiredException(){
        transactionPropagationRequiredManager.noTransactionRequiredRequiredException();
    }

    @Test
    public void testTransactionExceptionRequiredRequired(){
        transactionPropagationRequiredManager.transactionExceptionRequiredRequired();
    }

    @Test
    public void testTransactionRequiredRequiredException(){
        transactionPropagationRequiredManager.transactionRequiredRequiredException();
    }

    @Test
    public void testTransactionRequiredRequiredExceptionTry(){
        transactionPropagationRequiredManager.transactionRequiredRequiredExceptionTry();
    }


}
