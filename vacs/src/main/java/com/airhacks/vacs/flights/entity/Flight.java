
package com.airhacks.vacs.flights.entity;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public class Flight {

    private String number;

    public Flight(String number) {
        this.number = number;
    }


    public Flight(JsonObject flightAsJson) {
        this.number = flightAsJson.getString("number", "-unknown-");
        if ("13".equalsIgnoreCase(this.number)) {
            throw new DangerousFlightException("Too dangerous: " + number);
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
