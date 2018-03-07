
package com.airhacks.vacs.flights.boundary;

import com.airhacks.vacs.flights.entity.Flight;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class FlightsManager {

    @PersistenceContext
    EntityManager em;

    public List<Flight> all() {
        return this.em.
                createNamedQuery(Flight.ALL, Flight.class).
                getResultList();
    }

    public Optional<Flight> getFlight(String number) {
        if (number == null || number.contains("7")) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.em.find(Flight.class, number));
    }

    public String save(Flight flight) {
        flight = this.em.merge(flight);
        return flight.getNumber();
    }

}
