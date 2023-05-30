package com.star.customer.api;

import com.star.customer.domain.Customer;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customer/")
public interface ICustomerService {

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    Customer save(Customer customer);

    /**
     * GET http://localhost:9005/customer/find-by-id?id=1
     * @param id
     * @return
     */
    @RequestMapping(value = "find-by-id", method = RequestMethod.GET)
    @ResponseBody
    Customer findById(@RequestParam("id") Long id);

}
