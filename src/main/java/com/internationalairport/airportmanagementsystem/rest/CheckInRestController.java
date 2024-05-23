package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCheckInDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCheckInDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.service.interfaces.CheckInService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class CheckInRestController {

    private CheckInService checkInService;

    private UserEntityService userEntityService;

    @Autowired
    public CheckInRestController(CheckInService theCheckInService, UserEntityService userEntityService){

        checkInService = theCheckInService;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/check_ins")
    public List<CheckIn> findAllCheckIns(){

        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return checkInService.findByPassengerId(passenger.getPassengerId());
        }

        return checkInService.findAll();
    }

    @GetMapping("/check_ins/{checkInId}")
    public CheckIn getCheckInById(@PathVariable int checkInId){
        CheckIn theCheckIn = checkInService.findById(checkInId);
        if(theCheckIn == null){
            throw new RuntimeException("Check In Id not found - " + checkInId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theCheckIn);
        return theCheckIn;
    }

    @PostMapping("/check_ins")
    public CheckIn addCheckIn(@RequestBody PostCheckInDto postCheckInDto){
        CheckIn theCheckIn = checkInService.save(postCheckInDto);
        return theCheckIn;
    }

    @PutMapping("/check_ins")
    public CheckIn updateCheckIn(@RequestBody PutCheckInDto putCheckInDto){
        CheckIn theCheckIn = checkInService.save(putCheckInDto);
        return theCheckIn;
    }

    @DeleteMapping("/check_ins/{checkInId}")
    public String deleteCheckInById(@PathVariable int checkInId){
        CheckIn tempCheckIn = checkInService.findById(checkInId);
        if(tempCheckIn == null){
            throw new RuntimeException("Id not found - " + checkInId);
        }
        checkInService.deleteById(checkInId);
        return "Deleted Check In id - " + checkInId;
    }

    @DeleteMapping("/check_ins")
    public String deleteAllCheckIns() {
        return checkInService.deleteAll();
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, CheckIn checkIn) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != checkIn.getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}
