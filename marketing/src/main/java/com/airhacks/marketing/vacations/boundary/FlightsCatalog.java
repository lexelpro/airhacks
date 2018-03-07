
package com.airhacks.marketing.vacations.boundary;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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

    @Resource
    ManagedExecutorService mes;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newBuilder().
                connectTimeout(1, TimeUnit.SECONDS).
                readTimeout(1, TimeUnit.SECONDS).
                executorService(mes).
                build();
        this.tut = this.client.
                target("http://localhost:8080/vacs/resources/flights");
    }

    public CompletionStage<JsonArray> all() {
        return this.tut.
                request(MediaType.APPLICATION_JSON).
                rx().
                get(JsonArray.class);
    }

}
