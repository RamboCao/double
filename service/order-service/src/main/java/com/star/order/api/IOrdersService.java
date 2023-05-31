package com.star.order.api;

import com.star.order.domain.Orders;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("order")
public interface IOrdersService {

    @POST
    @Path("save")
    Orders save(Orders orders);

    /**
     * 添加了 jersey:application-path: ${spring.application.name} 所以需要添加 order-service
     * GET http://localhost:9006/order-service/order/find/1
     * @param id
     * @return
     */
    @GET
    @Path("find/{id:\\d+}")
    Orders findById(@PathParam("id") Long id);

    /**
     * GET http://localhost:9006/order-service/order/find/202210241442-1/1
     * @param code
     * @param customerId
     * @return
     */
    @GET
    @Path("find/{code}/{customerId}")
    List<Orders> findByCodeAndCustomerId(@PathParam("code") String code, @PathParam("customerId") Long customerId);

    /**
     * GET http://localhost:9006/order-service/order/find-by-code?code=202210241442-1
     * 不能使用 @RequestParam 应该使用 @QueryParam
     */
    @GET
    @Path("find-by-code")
    Orders findByCode(@QueryParam("code") String code);

}
