
package com.airhacks.vacs.flights.boundary;

import com.airhacks.vacs.flights.entity.Flight;
import com.airhacks.vacs.monitoring.control.MethodPerformanceSensor;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Interceptors(MethodPerformanceSensor.class)
public class FlightsManager {

    @PersistenceContext
    EntityManager em;
    
    @Inject
    Event<Flight> metrics;

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
        this.metrics.fire(flight);
        return flight.getNumber();
    }

}
