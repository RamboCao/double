package com.star.order.api;

import com.star.order.domain.Dog;
import com.star.order.domain.Orders;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("dog")
public interface IDogService {

    @GET
    @Path("find/{id:\\d+}")
    Dog findById(@PathParam("id") Long id);

}
