package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class AircraftRestController {

    private AircraftService aircraftService;

    @Autowired
    public AircraftRestController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @Operation(
            description = "Get endpoint to retrieve all aircraft. This endpoint returns a list of all aircraft registered in the system.",
            summary = "Retrieve all aircraft",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/aircrafts")
    public List<Aircraft> findAll() {
        return aircraftService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve an aircraft by its ID. This is useful for fetching the details of a specific aircraft.",
            summary = "Retrieve an aircraft by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Aircraft not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/aircrafts/{aircraftId}")
    public Aircraft getAircraftById(@PathVariable int aircraftId) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found for id - " + aircraftId);
        }
        return aircraft;
    }

    @Operation(
            description = "Post endpoint to add a new aircraft. This allows for the creation of a new aircraft in the system.",
            summary = "Add a new aircraft",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/aircrafts")
    public Aircraft addAircraft(@RequestBody PostAircraftDto postAircraftDto) {
        return aircraftService.save(postAircraftDto);
    }

    @Operation(
            description = "Put endpoint to update an existing aircraft. This can be used to modify the details of an aircraft, such as its model or status.",
            summary = "Update an aircraft",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Aircraft not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/aircrafts")
    public Aircraft updateAircraft(@RequestBody PutAircraftDto putAircraftDto) {
        return aircraftService.save(putAircraftDto);
    }

    @Operation(
            description = "Delete endpoint to remove an aircraft by its ID. This is useful for deleting a specific aircraft that is no longer needed.",
            summary = "Delete an aircraft by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Aircraft not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/aircrafts/{aircraftId}")
    public String deleteAircraftById(@PathVariable int aircraftId) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found for id - " + aircraftId);
        }
        aircraftService.deleteById(aircraftId);
        return "Deleted aircraft with id - " + aircraftId;
    }

    @Operation(
            description = "Delete endpoint to remove all aircraft. This is useful for bulk deletion of all aircraft in the system.",
            summary = "Delete all aircraft",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/aircrafts")
    public String deleteAllAircrafts() {
        return aircraftService.deleteAll();
    }
}
