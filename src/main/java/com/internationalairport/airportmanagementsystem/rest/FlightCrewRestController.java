package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
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

    @PostMapping("/flight_crews")
    public Flight addFlightCrew(@RequestBody PostFlightCrewDto postFlightCrewDto) {
        return flightCrewService.save(postFlightCrewDto);
    }

    @DeleteMapping("/flight_crews/{flightId}/{employeeId}")
    public String deleteFlightCrewById(@PathVariable int flightId, @PathVariable int employeeId) {
        flightCrewService.deleteByFlightIdAndEmployeeId(flightId, employeeId);
        return "Deleted flight crew with id - " +  flightId + "-" + employeeId;
    }


}

