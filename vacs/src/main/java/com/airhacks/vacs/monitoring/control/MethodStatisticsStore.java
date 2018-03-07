
package com.airhacks.vacs.monitoring.control;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("monitoring")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MethodStatisticsStore {

    ConcurrentMap<String, Long> lastInvocations;

    @PostConstruct
    public void init() {
        this.lastInvocations = new ConcurrentHashMap<>();
        System.out.println("this = " + this);
    }

    public void addStatistics(String method, long duration) {
        this.lastInvocations.put(method, duration);
    }

    @GET
    public JsonObject stats() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        this.lastInvocations.
                entrySet().
                stream().
                forEach(e -> builder.add(e.getKey(), e.getValue()));
        return builder.build();
    }



}
