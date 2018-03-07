
package com.airhacks.marketing.vacations.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("flights")
public class FlightsResource {

    @Inject
    FlightsCatalog catalog;

}
