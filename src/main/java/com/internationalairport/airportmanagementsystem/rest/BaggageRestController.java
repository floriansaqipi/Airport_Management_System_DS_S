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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class BaggageRestController{

    private BaggageService baggageService;

    private UserEntityService userEntityService;
    private PassengerService passengerService;

    @Autowired
    public BaggageRestController(BaggageService theBaggageService, UserEntityService userEntityService, PassengerService passengerService){
        baggageService = theBaggageService;
        this.userEntityService = userEntityService;
        this.passengerService = passengerService;
    }

    @GetMapping("/baggage")
    public List<Baggage> findAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntityService.findByUsername(username);
        if(user.getRole().getRoleName().equals("PASSENGER")){
            Passenger passenger = user.getPassenger();
            return baggageService.findByPassengerId(passenger.getPassengerId());
        }

        return baggageService.findAll();
    }

    @GetMapping("/baggage/{baggageId}")
    public Baggage getBaggage(@PathVariable int baggageId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntityService.findByUsername(username);

        Baggage theBaggage = baggageService.findById(baggageId);


        if(theBaggage == null){
            throw new RuntimeException("Baggage id not found - " + theBaggage);
        }
        if(user.getRole().getRoleName().equals("PASSENGER")){
            Passenger passenger = user.getPassenger();
            if(passenger.getPassengerId() != theBaggage.getPassenger().getPassengerId()){
                throw new AuthorizationException("You don't have access to this resource");
            }
        }

        return theBaggage;
    }

    @PostMapping("/baggage")
    public Baggage addBaggage(@RequestBody PostBaggageDto postBaggageDto){
        return baggageService.save(postBaggageDto);
    }

    @PutMapping("/baggage")
    public Baggage updateBaggage(@RequestBody PutBaggageDto putBaggageDto){
        Baggage dbBaggage = baggageService.save(putBaggageDto);
        return dbBaggage;
    }

    @DeleteMapping("/baggage/{baggageId}")
    public String deleteBaggage(@PathVariable int baggageId){
        Baggage tempBaggage = baggageService.findById(baggageId);
        if(tempBaggage == null){
            throw new RuntimeException("Cargo id not found - " + baggageId);
        }
        baggageService.deleteById(baggageId);
        return "Deleted Baggage id - " + baggageId;
    }

}
