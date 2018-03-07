
package com.airhacks.marketing.vacations.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class FlightsCatalog {

    private Client client;
    private WebTarget tut;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/vacs/resources/flights");
    }

    public void all() {
        Response getResponse = this.tut.request(MediaType.APPLICATION_JSON).get();
        JsonArray flightsArray = getResponse.readEntity(JsonArray.class);
    }

}
