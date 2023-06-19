package com.star.customer.api;

import com.star.customer.domain.Customer;
import com.star.customer.domain.Subscriber;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("customer-trx-with-propagation")
public interface ICustomerServiceWithTrxPropagation {

    @Path("add-required")
    void addRequired(Customer customer);


    @Path("add-requires-new")
    void addRequiresNew(Customer customer);

    @Path("add-nested")
    void addNested(Customer customer);

    @Path("add-mandatory")
    void addMandatory(Customer customer);

    @Path("add-supports")
    void addSupports(Customer customer);

    @Path("add-never")
    void addNever(Customer customer);
}
