package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.service.interfaces.BaggageService;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class BaggageRestController {

    private BaggageService baggageService;
    private UserEntityService userEntityService;
    private PassengerService passengerService;

    @Autowired
    public BaggageRestController(BaggageService theBaggageService, UserEntityService userEntityService, PassengerService passengerService) {
        baggageService = theBaggageService;
        this.userEntityService = userEntityService;
        this.passengerService = passengerService;
    }


    @Operation(
            description = "Get endpoint to retrieve all baggage records. If the user is a passenger, it returns only their baggage records.",
            summary = "Retrieve all baggage records",
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
    @GetMapping("/baggage")
    public List<Baggage> findAll() {
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return baggageService.findByPassengerId(passenger.getPassengerId());
        }

        return baggageService.findAll();
    }


    @Operation(
            description = "Get endpoint to retrieve a baggage record by its ID. Only the owner of the baggage (if a passenger) or an authorized user can access it.",
            summary = "Retrieve a baggage record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/baggage/{baggageId}")
    public Baggage getBaggage(@PathVariable int baggageId) {

        Baggage theBaggage = baggageService.findById(baggageId);
        if (theBaggage == null) {
            throw new RuntimeException("Baggage id not found - " + baggageId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theBaggage);

        return theBaggage;
    }


    @Operation(
            description = "Post endpoint to add a new baggage record. This allows for the creation of a new baggage record.",
            summary = "Add a new baggage record",
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
    @PostMapping("/baggage")
    public Baggage addBaggage(@RequestBody PostBaggageDto postBaggageDto) {
        return baggageService.save(postBaggageDto);
    }

    @Operation(
            description = "Put endpoint to update an existing baggage record. Only the owner of the baggage (if a passenger) or an authorized user can update it.",
            summary = "Update a baggage record",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/baggage")
    public Baggage updateBaggage(@RequestBody PutBaggageDto putBaggageDto) {
        Baggage theBaggage = baggageService.findById(putBaggageDto.baggageId());
        if (theBaggage == null) {
            throw new RuntimeException("Baggage id not found - " + putBaggageDto.baggageId() );
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theBaggage);
        Baggage dbBaggage = baggageService.save(putBaggageDto);
        return dbBaggage;
    }

    @Operation(
            description = "Delete endpoint to remove a baggage record by its ID. Only the owner of the baggage (if a passenger) or an authorized user can delete it.",
            summary = "Delete a baggage record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/baggage/{baggageId}")
    public String deleteBaggage(@PathVariable int baggageId) {
        Baggage theBaggage = baggageService.findById(baggageId);
        if (theBaggage == null) {
            throw new RuntimeException("Baggage id not found - " + baggageId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theBaggage);
        baggageService.deleteById(baggageId);
        return "Deleted Baggage id - " + baggageId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, Baggage baggage) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != baggage.getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }

}
