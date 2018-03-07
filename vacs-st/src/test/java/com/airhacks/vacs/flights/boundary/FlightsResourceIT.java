/*
 */
package com.airhacks.vacs.flights.boundary;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class FlightsResourceIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/vacs/resources/flights");
    }

    @Test
    public void all() {
        Response getResponse = this.tut.request(MediaType.APPLICATION_JSON).get();
        assertThat(getResponse.getStatus(), is(200));
        JsonArray flightsArray = getResponse.readEntity(JsonArray.class);
        assertFalse(flightsArray.isEmpty());
        System.out.println("flightsArray = " + flightsArray);
    }

    @Test
    public void empty() {
        Response emptyResponse = this.tut.path("7").request(MediaType.APPLICATION_JSON).get();
        assertThat(emptyResponse.getStatus(), is(204));
    }

    @Test
    public void bigObject() {
        Response bigResponse = this.tut.path("42").request(MediaType.APPLICATION_JSON).get();
        assertThat(bigResponse.getStatus(), is(200));
        JsonObject bigObject = bigResponse.readEntity(JsonObject.class);
        assertNotNull(bigObject.getJsonObject("flight"));
        assertNotNull(bigObject.getString("metadata"));

        System.out.println("bigObject = " + bigObject);
    }

}
