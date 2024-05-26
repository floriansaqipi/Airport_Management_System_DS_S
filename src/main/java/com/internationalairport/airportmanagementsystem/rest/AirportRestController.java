package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Airport")
public class AirportRestController {
    private AirportService airportService;

    @Autowired
    public AirportRestController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Operation(
            description = "Get endpoint for retrieving all airports",
            summary = "This is an endpoint used to get all the airports",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airports")
    public List<Airport> findAll() {
        return airportService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve an airport by its ID",
            summary = "This endpoint is used to retrieve an airport by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airports/{airportId}")
    public Airport getAirportById(@PathVariable int airportId) {
        Airport airport = airportService.findById(airportId);
        if (airport == null) {
            throw new RuntimeException("Airport not found for id - " + airportId);
        }
        return airport;
    }


    @Operation(
            description = "Post endpoint to add a new airport",
            summary = "This endpoint is used to add a new airport",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/airports")
    public Airport addAirport(@RequestBody PostAirportDto postAirportDto) {
        return airportService.save(postAirportDto);
    }

    @Operation(
            description = "Put endpoint to update an existing airport",
            summary = "This enpoint is used to update an airport",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/airports")
    public Airport updateAirport(@RequestBody PutAirportDto puttAirportDto) {
        return airportService.save(puttAirportDto);
    }


    @Operation(
            description = "Delete endpoint to remove an airport by its ID",
            summary = "This endpoint is used to delete an airport by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airports/{airportId}")
    public String deleteAirportById(@PathVariable int airportId) {
        Airport airport = airportService.findById(airportId);
        if (airport == null) {
            throw new RuntimeException("Airport not found for id - " + airportId);
        }
        airportService.deleteById(airportId);
        return "Deleted airport with id - " + airportId;
    }

    @Operation(
            description = "Delete endpoint to remove all airports",
            summary = "This endpoint is used to delete all airports",
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
    @DeleteMapping("/private/airports")
    public String deleteAllAirports() {
        return airportService.deleteAll();
    }
}
