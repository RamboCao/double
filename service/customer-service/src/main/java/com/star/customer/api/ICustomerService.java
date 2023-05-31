package com.star.customer.api;

import com.star.customer.domain.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/customer")
public interface ICustomerService {

    @POST
    @Path("save")
    Customer save(Customer customer);

    /**
     * GET http://localhost:9005/customer/find-by-id?id=1
     * @link @QueryParam
     * @link @PathParam
     * @link @PathVariable
     * @param id 用户标识
     * @return 用户
     */
    @GET
    @Path("/find-by-id/{id : \\d+}")
    Customer findById(@PathParam("id") Long id);

}
