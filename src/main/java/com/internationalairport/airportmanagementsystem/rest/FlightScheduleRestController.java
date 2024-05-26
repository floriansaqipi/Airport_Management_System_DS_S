package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightScheduleRestController {

    private final FlightScheduleService flightScheduleService;

    @Autowired
    public FlightScheduleRestController(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @Operation(
            description = "Endpoint to get all flight schedules",
            summary = "Retrieve all flight schedules",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all flight schedules",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/flight_schedules")
    public List<FlightSchedule> findAllFlightSchedules() {
        return flightScheduleService.findAll();
    }

    @Operation(
            description = "Endpoint to get a flight schedule by ID",
            summary = "Retrieve a flight schedule by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Flight Schedule ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/flight_schedules/{flightScheduleId}")
    public FlightSchedule getFlightScheduleById(@PathVariable int flightScheduleId) {
        return flightScheduleService.findById(flightScheduleId);
    }

    @Operation(
            description = "Endpoint to add a new flight schedule",
            summary = "Add a new flight schedule",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/flight_schedules")
    public FlightSchedule addFlightSchedule(@RequestBody PostFlightScheduleDto postFlightScheduleDto) {
        return flightScheduleService.save(postFlightScheduleDto);
    }

    @Operation(
            description = "Endpoint to update a flight schedule",
            summary = "Update a flight schedule",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/flight_schedules")
    public FlightSchedule updateFlightSchedule(@RequestBody PutFlightScheduleDto putFlightScheduleDto) {
        return flightScheduleService.save(putFlightScheduleDto);
    }

    @Operation(
            description = "Endpoint to delete a flight schedule by ID",
            summary = "Delete a flight schedule by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Flight Schedule ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/flight_schedules/{flightScheduleId}")
    public String deleteFlightScheduleById(@PathVariable int flightScheduleId) {
        flightScheduleService.deleteById(flightScheduleId);
        return "Deleted flight schedule id - " + flightScheduleId;
    }

    @Operation(
            description = "Endpoint to delete all flight schedules",
            summary = "Delete all flight schedules",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all flight schedules",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/flight_schedules")
    public String deleteAllFlightSchedules() {
        return flightScheduleService.deleteAll();
    }
}
