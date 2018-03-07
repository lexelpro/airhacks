
package com.airhacks.vacs.flights.boundary;

import com.airhacks.vacs.flights.entity.Flight;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class FlightsManager {

    public List<Flight> all() {
        return Arrays.asList(new Flight("lh42"), new Flight("lh-4"));
    }


}
