package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightRestController {

    private FlightService flightService;

    @Autowired
    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> findAllFlights() {
        return flightService.findAll();
    }

    @GetMapping("/flights/{flightId}")
    public Flight getFlightById(@PathVariable Integer flightId) {
        Flight flight = flightService.findById(flightId);
        if (flight == null) {
            throw new RuntimeException("Flight not found for id - " + flightId);
        }
        return flight;
    }

    @PostMapping("/flights")
    public Flight addFlight(@RequestBody Flight flight) {
        flight.setFlightId(0);
        return flightService.save(flight);
    }

    @PutMapping("/flights")
    public Flight updateFlight(@RequestBody Flight flight) {
        return flightService.save(flight);
    }

    @DeleteMapping("/flights/{flightId}")
    public String deleteFlightById(@PathVariable Integer flightId) {
        Flight flight = flightService.findById(flightId);
        if (flight == null) {
            throw new RuntimeException("Flight not found for id - " + flightId);
        }
        flightService.deleteById(flightId);
        return "Deleted flight with id - " + flightId;
    }

    @DeleteMapping("/flights")
    public String deleteAllFlights() {
        return flightService.deleteAll();
    }
}