package com.airhacks.ping.boundary;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @Inject
    SecurityContext securityContext;

    @GET
    public Response ping() {

        if (securityContext.getCallerPrincipal() != null) {
            String userName = securityContext.getCallerPrincipal().getName();
            JsonObject result = Json.createObjectBuilder()
                    .add("user", userName)
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }


}
