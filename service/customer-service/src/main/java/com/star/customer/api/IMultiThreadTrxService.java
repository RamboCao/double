package com.star.customer.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Caolp
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("multi-thread-trx")
public interface IMultiThreadTrxService {

    @GET
    @Path("multi-thread-trx/save")
    void multiThreadTrx();
}
