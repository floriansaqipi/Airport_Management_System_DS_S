package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.service.interfaces.BaggageService;
import com.internationalairport.airportmanagementsystem.service.interfaces.BoardingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardingPassRestController {

    private BoardingPassService boardingPassService;

    @Autowired
    public BoardingPassRestController(BoardingPassService theBoardingPassService){
        boardingPassService = theBoardingPassService;
    }

    @GetMapping("/boarding_pass")
    public List<BoardingPass> findAll(){
        return boardingPassService.findAll();
    }

    @GetMapping("/boarding_pass/{boarding_passId}")
    public BoardingPass getBoardingPass(@PathVariable int boarding_passId){
        BoardingPass theBoardingPass = boardingPassService.findById(boarding_passId);
        if(theBoardingPass == null){
            throw new RuntimeException("Cargo id not found - " + boarding_passId);
        }
        return theBoardingPass;
    }

    @PostMapping("/boarding_pass")
    public BoardingPass addBoardingPass(@RequestBody BoardingPass theBoardingPass){
        theBoardingPass.setBoardingPassId(0);
        BoardingPass boarding_pass = boardingPassService.save(theBoardingPass);
        return boarding_pass;
    }

    @PutMapping("/boarding_pass")
    public BoardingPass updateBoardingPass(@RequestBody BoardingPass theBoardingPass){
        BoardingPass dbBoardingPass = boardingPassService.save(theBoardingPass);
        return dbBoardingPass;
    }

    @DeleteMapping("/boarding_pass/{boarding_passId}")
    public String deleteBoardingPass(@PathVariable int boarding_passId){
        BoardingPass tempBoardingPass = boardingPassService.findById(boarding_passId);
        if(tempBoardingPass == null){
            throw new RuntimeException("Cargo id not found - " + boarding_passId);
        }
        boardingPassService.deleteById(boarding_passId);
        return "Deleted Cargo id - " + boarding_passId;
    }
}
