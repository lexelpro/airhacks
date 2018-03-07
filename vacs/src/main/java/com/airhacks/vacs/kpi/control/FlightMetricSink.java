
package com.airhacks.vacs.kpi.control;

import com.airhacks.vacs.flights.entity.Flight;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("metrics")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class FlightMetricSink {

    private AtomicInteger flightCreationCounter;
    private AtomicInteger flightFailureCounter;

    @PostConstruct
    public void init() {
        this.flightCreationCounter = new AtomicInteger();
        this.flightFailureCounter = new AtomicInteger();
    }

    public void onNewFlight(@Observes(during = TransactionPhase.AFTER_SUCCESS) Flight flight) {
        this.flightCreationCounter.incrementAndGet();
    }
    public void onFailedFlightCreation(@Observes(during = TransactionPhase.AFTER_FAILURE) Flight flight) {
        this.flightFailureCounter.incrementAndGet();
    }

    @GET
    @Path("created-flights")
    public int flights() {
        return this.flightCreationCounter.get();
    }
    @GET
    @Path("failed-flights")
    public int failedFlights() {
        return this.flightFailureCounter.get();
    }


}
