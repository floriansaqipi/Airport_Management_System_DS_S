package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class FlightCrewRestController {

    private FlightCrewService flightCrewService;

    @Autowired
    public FlightCrewRestController(FlightCrewService flightCrewService) {
        this.flightCrewService = flightCrewService;
    }

    @Operation(
            description = "Endpoint to add flight crew",
            summary = "Add flight crew",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the flight crew",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/flight_crews")
    public Flight addFlightCrew(@RequestBody PostFlightCrewDto postFlightCrewDto) {
        return flightCrewService.save(postFlightCrewDto);
    }

    @Operation(
            description = "Endpoint to delete flight crew by IDs",
            summary = "Delete flight crew by IDs",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the flight crew",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Flight or Employee ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/flight_crews/{flightId}/{employeeId}")
    public String deleteFlightCrewById(@PathVariable int flightId, @PathVariable int employeeId) {
        flightCrewService.deleteByFlightIdAndEmployeeId(flightId, employeeId);
        return "Deleted flight crew with id - " +  flightId + "-" + employeeId;
    }


}

