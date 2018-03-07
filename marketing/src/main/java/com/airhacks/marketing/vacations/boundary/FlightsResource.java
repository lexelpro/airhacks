
package com.airhacks.marketing.vacations.boundary;

import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
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

    @GET
    public void flights(@Suspended AsyncResponse response) {
        response.setTimeout(2, TimeUnit.SECONDS);

        this.catalog.all().thenAccept(response::resume);

    }


}
