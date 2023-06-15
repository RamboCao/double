package com.star.customer.api;

import com.star.customer.domain.Customer;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("customer-trx")
public interface ICustomerServiceWithTrx {

    @POST
    @Path("save")
    Customer save(Customer customer);

    @POST
    @Path("public-method")
    void publicMethod(Long id);

    @POST
    @Path("inner-invoke-method-trx")
    void innerInvokeMethodTrx();

}
