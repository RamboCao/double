package com.star.customer;

import com.star.customer.api.CustomerServiceWithTrx;
import com.star.customer.api.IMultiThreadTrxService;
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

    @Test
    public void testPrivateMethod() throws Exception {
        serviceWithTrx.saveWithCustomizePropagationException();
    }


    @Test
    public void testMultiThreadTrxService(){
        multiThreadTrxService.multiThreadTrx();
    }
}
