package com.star.customer;

import com.star.customer.api.CustomerServiceWithTrx;
import com.star.customer.api.IMultiThreadTrxService;
import com.star.customer.manager.*;
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

    @Autowired
    private TransactionPropagationRequiresNewManager transactionPropagationRequiresNewManager;

    @Autowired
    private TransactionPropagationNestedManager transactionPropagationNestedManager;

    @Autowired
    private TransactionPropagationMandatoryManager transactionPropagationMandatoryManager;

    @Autowired
    private TransactionPropagationSupportsManager transactionPropagationSupportsManager;

    @Autowired
    private TransactionPropagationNeverManager transactionPropagationNeverManager;

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

    @Test
    public void testNoTransactionExceptionRequiresNewRequiresNew(){
        transactionPropagationRequiresNewManager.noTransactionExceptionRequiresNewRequiresNew();
    }

    @Test
    public void testNoTransactionRequiresNewRequiresNewException(){
        transactionPropagationRequiresNewManager.noTransactionRequiresNewRequiresNewException();
    }

    @Test
    public void testTransactionExceptionRequiresNewRequireNew(){
        transactionPropagationRequiresNewManager.transactionExceptionRequiresNewRequireNew();
    }

    @Test
    public void testTransactionRequiredRequiresNewRequiresNewException(){
        transactionPropagationRequiresNewManager.transactionRequiredRequiresNewRequiresNewException();
    }

    @Test
    public void testTransactionRequiredRequiresNewRequiresNewExceptionTry(){
        transactionPropagationRequiresNewManager.transactionRequiredRequiresNewRequiresNewExceptionTry();
    }

    @Test
    public void testNoTransactionExceptionNestedNested(){
        transactionPropagationNestedManager.noTransactionExceptionNestedNested();
    }

    @Test
    public void testNoTransactionNestedNestedException(){
        transactionPropagationNestedManager.noTransactionNestedNestedException();
    }

    @Test
    public void testTransactionExceptionNestedNested(){
        transactionPropagationNestedManager.transactionExceptionNestedNested();
    }

    @Test
    public void testTransactionNestedNestedException(){
        transactionPropagationNestedManager.transactionNestedNestedException();
    }

    @Test
    public void testTransactionNestedNestedExceptionTry(){
        transactionPropagationNestedManager.transactionNestedNestedExceptionTry();
    }

    @Test
    public void testNoTransactionExceptionMandatoryMandatory(){
        transactionPropagationMandatoryManager.noTransactionExceptionMandatoryMandatory();
    }

    @Test
    public void testNoTransactionMandatoryMandatoryException(){
        transactionPropagationMandatoryManager.noTransactionMandatoryMandatoryException();
    }

    @Test
    public void testTransactionExceptionMandatoryMandatory(){
        transactionPropagationMandatoryManager.transactionExceptionMandatoryMandatory();
    }

    @Test
    public void testTransactionMandatoryMandatoryException(){
        transactionPropagationMandatoryManager.transactionMandatoryMandatoryException();
    }

    @Test
    public void testTransactionMandatoryMandatoryExceptionTry(){
        transactionPropagationMandatoryManager.transactionMandatoryMandatoryExceptionTry();
    }

    @Test
    public void testNoTransactionExceptionSupportsSupports(){
        transactionPropagationSupportsManager.noTransactionExceptionSupportsSupports();
    }

    @Test
    public void testNoTransactionSupportsSupportsException(){
        transactionPropagationSupportsManager.noTransactionSupportsSupportsException();
    }

    @Test
    public void testTransactionExceptionSupportsSupports(){
        transactionPropagationSupportsManager.transactionExceptionSupportsSupports();
    }

    @Test
    public void testTransactionSupportsSupportsException(){
        transactionPropagationSupportsManager.transactionSupportsSupportsException();
    }

    @Test
    public void testTransactionSupportsSupportsExceptionTry(){
        transactionPropagationSupportsManager.transactionSupportsSupportsExceptionTry();
    }

    @Test
    public void testNoTransactionExceptionNeverNever(){
        transactionPropagationNeverManager.noTransactionExceptionNeverNever();
    }

    @Test
    public void testNoTransactionNeverNeverException(){
        transactionPropagationNeverManager.noTransactionNeverNeverException();
    }

    @Test
    public void testTransactionExceptionNeverNever(){
        transactionPropagationNeverManager.transactionExceptionNeverNever();
    }
}
