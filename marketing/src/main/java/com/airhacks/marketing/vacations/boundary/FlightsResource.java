
package com.airhacks.marketing.vacations.boundary;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("flights")
public class FlightsResource {

    @Inject
    FlightsCatalog catalog;

    @Resource
    ManagedExecutorService mes;


    @GET
    public void flights(@Suspended AsyncResponse response) {
        response.setTimeout(2, TimeUnit.SECONDS);
        supplyAsync(this.catalog::all, mes).
                thenAccept(response::resume);


    }


}
