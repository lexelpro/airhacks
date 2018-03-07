
package com.airhacks.vacs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author airhacks.com
 */
@Provider
public class IllegalStateExceptonMapper implements ExceptionMapper<IllegalStateException> {

    @Override
    public Response toResponse(IllegalStateException exception) {
        return Response.
                status(418).
                header("info", "not as illegal").
                header("cause", exception.getMessage()).
                build();
    }

}
