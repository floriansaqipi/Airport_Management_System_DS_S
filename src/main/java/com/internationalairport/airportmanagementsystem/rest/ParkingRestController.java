package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingRestController {

    private ParkingService parkingService;

    @Autowired
    public ParkingRestController(ParkingService theParkingService){
        parkingService=theParkingService;
    }

    @Operation(
            description = "Endpoint to get all parkings",
            summary = "Retrieve all parkings",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all parkings",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/parkings")
    public List<Parking> findAllParkings(){
        return parkingService.findAll();
    }

    @Operation(
            description = "Endpoint to get a parking by ID",
            summary = "Retrieve a parking by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Parking ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/parkings/{parkingId}")
    public Parking getParkingServiceById(@PathVariable int parkingId){
        Parking theParking=parkingService.findById(parkingId);
        if(theParking==null){
            throw new RuntimeException("Id not found - "+parkingId);
        }
        return theParking;
    }

    @Operation(
            description = "Endpoint to add a new parking",
            summary = "Add a new parking",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/parkings")
    public Parking addParking(@RequestBody PostParkingDto postParkingDto){
        return parkingService.save(postParkingDto);
    }

    @Operation(
            description = "Endpoint to update a parking",
            summary = "Update a parking",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/parkings")
    public Parking updateParking(@RequestBody PutParkingDto putParkingDto){
        return parkingService.save(putParkingDto);
    }

    @Operation(
            description = "Endpoint to delete a parking by ID",
            summary = "Delete a parking by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Parking ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/parkings/{parkingId}")
    public String deleteParkingById(@PathVariable int parkingId){
        Parking parking = parkingService.findById(parkingId);
        if(parking==null){
            throw new RuntimeException("Id not found - "+parkingId);

        }
        parkingService.deleteById(parkingId);
        return "Deleted Parking id - "+parkingId;
    }
    @Operation(
            description = "Endpoint to delete all parkings",
            summary = "Delete all parkings",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all parkings",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/parkings")
    public String deleteAllParkings() {
        return parkingService.deleteAll();
    }
}
