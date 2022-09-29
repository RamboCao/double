package com.star.customer.api;

import com.star.customer.domain.Customer;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customer/")
@RestController
public interface ICustomerService {

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    Customer save(Customer customer);

    @RequestMapping(value = "find-by-id", method = RequestMethod.GET)
    @ResponseBody
    Customer findById(@RequestParam("id") Long id);

}
