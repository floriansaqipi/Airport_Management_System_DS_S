package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.exceptions.FlightAlreadyExistsException;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(
            description = "Endpoint to get all flights",
            summary = "Retrieve all flights",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all flights",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/flights")
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @Operation(
            description = "Endpoint to get a flight by ID",
            summary = "Retrieve a flight by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Flight ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/flights/{flightId}")
    public Flight getFlightById(@PathVariable Integer flightId) {
        Flight flight = flightService.findById(flightId);
        if (flight == null) {
            throw new RuntimeException("Flight not found for id - " + flightId);
        }
        return flight;
    }

    @Operation(
            description = "Endpoint to add a new flight",
            summary = "Add a new flight",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/flights")
    public Flight addFlight(@RequestBody PostFlightDto postFlightDto) {
        if (flightService.existsByFlightNumber(postFlightDto.flightNumber())) {
            throw new FlightAlreadyExistsException("Flight with that number already exists!");
        }
        return flightService.save(postFlightDto);
    }

    @Operation(
            description = "Endpoint to update a flight",
            summary = "Update a flight",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/flights")
    public Flight updateFlight(@RequestBody PutFlightDto putFlightDto) {
        return flightService.save(putFlightDto);
    }

    @Operation(
            description = "Endpoint to delete a flight by ID",
            summary = "Delete a flight by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Flight ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/flights/{flightId}")
    public String deleteFlightById(@PathVariable Integer flightId) {
        Flight flight = flightService.findById(flightId);
        if (flight == null) {
            throw new RuntimeException("Flight not found for id - " + flightId);
        }
        flightService.deleteById(flightId);
        return "Deleted flight with id - " + flightId;
    }

    @Operation(
            description = "Endpoint to delete all flights",
            summary = "Delete all flights",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all flights",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/flights")
    public String deleteAllFlights() {
        return flightService.deleteAll();
    }
}