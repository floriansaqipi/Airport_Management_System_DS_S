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

    @GetMapping("/boarding_passes")
    public List<BoardingPass> findAll(){
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return boardingPassService.findByPassengerId(passenger.getPassengerId());
        }

        return boardingPassService.findAll();
    }

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

    @PostMapping("/boarding_passes")
    public BoardingPass addBoardingPass(@RequestBody PostBoardingPassDto postBoardingPassDto){
        return boardingPassService.save(postBoardingPassDto);
    }

    @PutMapping("/boarding_passes")
    public BoardingPass updateBoardingPass(@RequestBody PutBoardingPassDto putBoardingPassDto){
        return boardingPassService.save(putBoardingPassDto);
    }

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
