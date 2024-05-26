package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.service.interfaces.BoardingPassService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class BoardingPassRestController {

    private BoardingPassService boardingPassService;

    private UserEntityService userEntityService;

    @Autowired
    public BoardingPassRestController(BoardingPassService theBoardingPassService, UserEntityService userEntityService){
        boardingPassService = theBoardingPassService;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Get endpoint to retrieve all boarding pass records. If the user is a passenger, it returns only their boarding passes.",
            summary = "Retrieve all boarding pass records",
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
    @GetMapping("/boarding_passes")
    public List<BoardingPass> findAll(){
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return boardingPassService.findByPassengerId(passenger.getPassengerId());
        }

        return boardingPassService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve a boarding pass by its ID. Only the owner of the boarding pass (if a passenger) or an authorized user can access it.",
            summary = "Retrieve a boarding pass by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/boarding_passes/{boarding_passId}")
    public BoardingPass getBoardingPass(@PathVariable int boarding_passId){

        BoardingPass theBoardingPass = boardingPassService.findById(boarding_passId);
        if(theBoardingPass == null){
            throw new RuntimeException("Boarding Pass id not found - " + boarding_passId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theBoardingPass);
        return theBoardingPass;
    }

    @Operation(
            description = "Post endpoint to add a new boarding pass. This allows for the creation of a new boarding pass.",
            summary = "Add a new boarding pass",
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
    @PostMapping("/boarding_passes")
    public BoardingPass addBoardingPass(@RequestBody PostBoardingPassDto postBoardingPassDto){
        return boardingPassService.save(postBoardingPassDto);
    }

    @Operation(
            description = "Put endpoint to update an existing boarding pass. Only the owner of the boarding pass (if a passenger) or an authorized user can update it.",
            summary = "Update a boarding pass",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/boarding_passes")
    public BoardingPass updateBoardingPass(@RequestBody PutBoardingPassDto putBoardingPassDto){
        return boardingPassService.save(putBoardingPassDto);
    }

    @Operation(
            description = "Delete endpoint to remove a boarding pass by its ID. Only the owner of the boarding pass (if a passenger) or an authorized user can delete it.",
            summary = "Delete a boarding pass by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/boarding_passes/{boarding_passId}")
    public String deleteBoardingPass(@PathVariable int boarding_passId){
        BoardingPass tempBoardingPass = boardingPassService.findById(boarding_passId);
        if(tempBoardingPass == null){
            throw new RuntimeException("Boarding Pass id not found - " + boarding_passId);
        }
        boardingPassService.deleteById(boarding_passId);
        return "Deleted Boarding Pass id - " + boarding_passId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, BoardingPass boardingPass) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != boardingPass.getTicket().getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}
