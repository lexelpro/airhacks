
package com.airhacks.marketing.vacations.boundary;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
        this.client = ClientBuilder.newBuilder().
                connectTimeout(1, TimeUnit.SECONDS).
                readTimeout(1, TimeUnit.SECONDS).
                build();
        this.tut = this.client.
                target("http://localhost:8080/vacs/resources/flights");
    }

    public JsonArray all() {
        return this.tut.
                request(MediaType.APPLICATION_JSON).
                get(JsonArray.class);
    }

}
