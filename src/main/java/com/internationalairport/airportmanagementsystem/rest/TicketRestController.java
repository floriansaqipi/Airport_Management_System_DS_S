package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairport.airportmanagementsystem.service.interfaces.TicketService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class TicketRestController {
    private TicketService ticketService;

    private UserEntityService userEntityService;
    private PassengerService passengerService;

    @Autowired
    public TicketRestController(TicketService theTicketService,
                                UserEntityService userEntityService, PassengerService passengerService){
        this.ticketService = theTicketService;
        this.userEntityService = userEntityService;
        this.passengerService = passengerService;
    }

    @Operation(
            description = "Endpoint to get all tickets",
            summary = "Get all tickets",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all tickets",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/tickets")
    public List<Ticket> findAll(){
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return ticketService.findByPassengerId(passenger.getPassengerId());
        }

        return ticketService.findAll();
    }

    @Operation(
            description = "Endpoint to get a ticket by ID",
            summary = "Get a ticket by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ticket ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/tickets/{ticketId}")
    public Ticket getTicket(@PathVariable int ticketId){

        Ticket theTicket = ticketService.findById(ticketId);
        if (theTicket == null) {
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theTicket);

        return theTicket;
    }

    @Operation(
            description = "Endpoint to add a new ticket",
            summary = "Add a new ticket",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody PostTicketDto postTicketDto){

        return ticketService.save(postTicketDto);

    }

    @Operation(
            description = "Endpoint to update a ticket",
            summary = "Update a ticket",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/tickets")
    public Ticket updateTicket(@RequestBody PutTicketDto putTicketDto){
        return ticketService.save(putTicketDto);
    }

    @Operation(
            description = "Endpoint to delete a ticket by ID",
            summary = "Delete a ticket by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ticket ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/tickets/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId){
        Ticket tempTicket = ticketService.findById(ticketId);
        if(tempTicket == null){
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        ticketService.deleteById(ticketId);
        return "Deleted Ticket id - " + ticketId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, Ticket ticket) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != ticket.getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}
