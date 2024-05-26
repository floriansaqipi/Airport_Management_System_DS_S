package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRentalServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRentalServiceDto;
import com.internationalairport.airportmanagementsystem.entities.RentalService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RentalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalServiceRestController {

    private RentalServiceService rentalServiceService;

    @Autowired
    public RentalServiceRestController(RentalServiceService theRentalService){
        rentalServiceService=theRentalService;
    }

    @Operation(
            description = "Endpoint to get all rental services",
            summary = "Retrieve all rental services",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all rental services",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/rental_services")
    public List<RentalService> findAllRentalServices(){
        return rentalServiceService.findAll();
    }

    @Operation(
            description = "Endpoint to get a rental service by ID",
            summary = "Retrieve a rental service by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Rental service ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/rental_services/{rentalServiceId}")
    public RentalService getRentalServiceById(@PathVariable int rentalServiceId){
        RentalService theRentalService=rentalServiceService.findById(rentalServiceId);
        if(theRentalService==null){
            throw new RuntimeException("Id not found - "+rentalServiceId);
        }
        return theRentalService;
    }

    @Operation(
            description = "Endpoint to add a new rental service",
            summary = "Add a new rental service",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/rental_services")
    public RentalService addRentalService(@RequestBody PostRentalServiceDto postRentalServiceDto){
        return rentalServiceService.save(postRentalServiceDto);
    }


    @Operation(
            description = "Endpoint to update a rental service",
            summary = "Update a rental service",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/rental_services")
    public RentalService updateRentalService(@RequestBody PutRentalServiceDto putRentalServiceDto){
        return rentalServiceService.save(putRentalServiceDto);
    }

    @Operation(
            description = "Endpoint to delete a rental service by ID",
            summary = "Delete a rental service by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Rental service ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/rental_services/{rentalServiceId}")
    public String deleteRentalServiceById(@PathVariable int rentalServiceId){
        RentalService rentalService = rentalServiceService.findById(rentalServiceId);
        if(rentalService==null){
            throw new RuntimeException("Id not found - "+rentalServiceId);

        }
        rentalServiceService.deleteById(rentalServiceId);
        return "Deleted Rental Service id - "+rentalServiceId;
    }

    @Operation(
            description = "Endpoint to delete all rental services",
            summary = "Delete all rental services",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all rental services",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/rental_services")
    public String deleteAllRentalServices() {
        return rentalServiceService.deleteAll();
    }
}
