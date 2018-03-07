
package com.airhacks.vacs.flights.entity;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@ApplicationException(rollback = true)
public class DangerousFlightException extends WebApplicationException {

    public DangerousFlightException(String message) {
        super(Response.status(400).header("info", message).build());
    }


}
