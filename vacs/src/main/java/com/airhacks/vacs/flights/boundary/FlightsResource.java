
package com.airhacks.vacs.flights.boundary;

import com.airhacks.vacs.flights.entity.Flight;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("flights")
public class FlightsResource {

    @Inject
    FlightsManager fm;

    @GET
    public JsonArray all() {
        JsonArrayBuilder retVal = Json.createArrayBuilder();
        this.fm.all().
                stream().
                map(Flight::toJSON).
                forEach(retVal::add);
        return retVal.build();
    }


}
