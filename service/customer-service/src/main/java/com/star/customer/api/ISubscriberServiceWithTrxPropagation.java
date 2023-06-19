package com.star.customer.api;

import com.star.customer.domain.Subscriber;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("subscriber-trx-with-propagation")
public interface ISubscriberServiceWithTrxPropagation {

    @Path("subscriber-add-required")
    void addRequired(Subscriber subscriber);

    @Path("subscriber-add-required-exception")
    void addRequiredException(Subscriber subscriber);

    @Path("subscriber-add-requires-new")
    void addRequiresNew(Subscriber subscriber);

    @Path("subscriber-add-requires-new-exception")
    void addRequiresNewException(Subscriber subscriber);

    @Path("subscriber-add-nested")
    void addNested(Subscriber subscriber);

    @Path("subscriber-add-nested-exception")
    void addNestedException(Subscriber subscriber);

    @Path("subscriber-add-mandatory")
    void addMandatory(Subscriber subscriber);

    @Path("subscriber-add-mandatory-exception")
    void addMandatoryException(Subscriber subscriber);

    @Path("subscriber-add-supports")
    void addSupports(Subscriber subscriber);

    @Path("subscriber-add-supports-exception")
    void addSupportsException(Subscriber subscriber);

}
