package com.star.order.api.kafka;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Caolp
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/kafka/producer/")
public interface IKafkaProducerService {

    @GET
    @Path("send/")
    void sendMessageSync(@QueryParam("topic") String topic, @QueryParam("message") String message) throws ExecutionException, InterruptedException, TimeoutException;

}
