package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportServiceRestController {

    private AirportServiceService airportServiceService;

    @Autowired
    public AirportServiceRestController(AirportServiceService theAirportServiceService){
        airportServiceService=theAirportServiceService;
    }

    @Operation(
            description = "Get endpoint to retrieve all airport services. This endpoint returns a list of all services available at the airport.",
            summary = "Retrieve all airport services",
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
    @GetMapping("/public/airport_services")
    public List<AirportService> findAllAirportServices(){
        return airportServiceService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve an airport service by its ID. This is useful for fetching the details of a specific airport service.",
            summary = "Retrieve an airport service by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airport_services/{airportServiceId}")
    public AirportService getAirportServiceById(@PathVariable int airportServiceId){
        AirportService theAirportService=airportServiceService.findById(airportServiceId);
        if(theAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);
        }
        return theAirportService;
    }

    @Operation(
            description = "Post endpoint to add a new airport service. This allows for the creation of a new service at the airport.",
            summary = "Add a new airport service",
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
    @PostMapping("/private/airport_services")
    public AirportService addAirportService(@RequestBody PostAirportServiceDto postAirportServiceDto){
        return airportServiceService.save(postAirportServiceDto);
    }

    @Operation(
            description = "Put endpoint to update an existing airport service. This can be used to modify the details of an airport service.",
            summary = "Update an airport service",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/airport_services")
    public AirportService updateAirportService(@RequestBody PutAirportServiceDto putAirportServiceDto){
        return airportServiceService.save(putAirportServiceDto);
    }

    @Operation(
            description = "Delete endpoint to remove an airport service by its ID. This is useful for deleting a specific service at the airport that is no longer needed.",
            summary = "Delete an airport service by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airport_services/{airportServiceId}")
    public String deleteAirportServiceById(@PathVariable int airportServiceId){
        AirportService tempAirportService = airportServiceService.findById(airportServiceId);
        if(tempAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);

        }
        airportServiceService.deleteById(airportServiceId);
        return "Deleted Airport Service id - "+airportServiceId;
    }

    @Operation(
            description = "Delete endpoint to remove all airport services. This is useful for bulk deletion of all services at the airport.",
            summary = "Delete all airport services",
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
    @DeleteMapping("/private/airport_services")
    public String deleteAllAirportServices() {
        return airportServiceService.deleteAll();
    }
}
