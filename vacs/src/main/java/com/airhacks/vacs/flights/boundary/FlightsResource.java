
package com.airhacks.vacs.flights.boundary;

import com.airhacks.vacs.flights.entity.Flight;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

    @GET
    @Path("{nbr}")
    public Response flight(@PathParam("nbr") String number) {
        return this.fm.getFlight(number).
                map(Flight::toJSON).
                map(this::makeBiggerObject).
                map(this::convert).
                orElseGet(this::empty);
    }

    @POST
    public Response save(JsonObject flightAsJson, @Context UriInfo info) {
        String number = this.fm.save(new Flight(flightAsJson));
        URI uri = info.getAbsolutePathBuilder().
                path("/" + number).
                build();
        return Response.created(uri).build();

    }


    Response empty() {
        return Response.status(204).header("info", "no vacations for you").build();
    }

    Response convert(JsonObject input) {
        return Response.status(200).
                entity(input).
                build();
    }

    JsonObject makeBiggerObject(JsonObject flight) {
        return Json.createObjectBuilder().
                add("flight", flight).
                add("metadata", "crazy").
                build();
    }


}
