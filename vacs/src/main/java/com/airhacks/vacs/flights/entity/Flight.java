
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

    public JsonObject toJSON() {
        return Json.createObjectBuilder().
                add("number", this.number).
                build();
    }


}
