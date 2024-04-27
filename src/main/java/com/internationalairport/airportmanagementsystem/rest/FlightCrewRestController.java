package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightCrewRestController {

    private FlightCrewService flightCrewService;

    @Autowired
    public FlightCrewRestController(FlightCrewService flightCrewService) {
        this.flightCrewService = flightCrewService;
    }

    @GetMapping("/flight_crews")
    public List<FlightCrew> findAllFlightCrews() {
        return flightCrewService.findAll();
    }

    @GetMapping("/flight_crews/{flightCrewId}")
    public FlightCrew getFlightCrewById(@PathVariable int flightCrewId) {
        FlightCrew flightCrew = flightCrewService.findById(flightCrewId);
        if (flightCrew == null) {
            throw new RuntimeException("Flight crew not found - " + flightCrewId);
        }
        return flightCrew;
    }

    @PostMapping("/flight_crews")
    public FlightCrew addFlightCrew(@RequestBody FlightCrew flightCrew) {
        flightCrew.setCrewId(0);
        return flightCrewService.save(flightCrew);
    }

    @PutMapping("/flight_crews")
    public FlightCrew updateFlightCrew(@RequestBody FlightCrew flightCrew) {
        return flightCrewService.save(flightCrew);
    }

    @DeleteMapping("/flight_crews/{flightCrewId}")
    public String deleteFlightCrewById(@PathVariable int flightCrewId) {
        flightCrewService.deleteById(flightCrewId);
        return "Deleted flight crew with id - " + flightCrewId;
    }

    @DeleteMapping("/flight_crews")
    public String deleteAllFlightCrews() {
        return flightCrewService.deleteAll();
    }
}

