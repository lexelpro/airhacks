
package com.airhacks.vacs.flights.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Flight.ALL, query = "SELECT f FROM Flight f")
public class Flight {

    @Id
    private String number;
    private final static String PREFIX = "com.airhacks.vacs.flights.entity.Flight.";
    public final static String ALL = PREFIX + "findAll";

    public Flight(String number) {
        this.number = number;
    }

    public Flight() {
    }

    public Flight(JsonObject flightAsJson) {
        this.number = flightAsJson.getString("number", "-unknown-");
        if ("13".equalsIgnoreCase(this.number)) {
            throw new DangerousFlightException("Too dangerous: " + number);
        }
        if ("7".equalsIgnoreCase(this.number)) {
            throw new IllegalStateException("my state is not legal " + number);
        }
    }

    public String getNumber() {
        return number;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder().
                add("number", this.number).
                build();
    }


}
