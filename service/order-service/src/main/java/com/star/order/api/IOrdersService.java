package com.star.order.api;

import com.star.order.domain.Orders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order/")
public interface IOrdersService {

    @RequestMapping(method = RequestMethod.POST, value = "save")
    Orders save(Orders orders);

    /**
     * GET http://localhost:9006/order/find/1
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "find/{id:\\d+}")
    @ResponseBody
    Orders findById(@PathVariable Long id);

    /**
     * GET http://localhost:9006/order/find/202210241442-1/1
     * @param code
     * @param customerId
     * @return
     */
    @RequestMapping(value = "find/{code}/{customerId}")
    @ResponseBody
    List<Orders> findByCodeAndCustomerId(@PathVariable String code, @PathVariable Long customerId);

    /**
     * GET http://localhost:9006/order/find-by-code?code=202210241442-1
     */
    @RequestMapping(value = "find-by-code", method = RequestMethod.GET)
    @ResponseBody
    Orders findByCode(@RequestParam("code") String code);

}
