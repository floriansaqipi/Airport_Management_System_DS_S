package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
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

    @GetMapping("/boarding_passes")
    public List<BoardingPass> findAll(){
        return boardingPassService.findAll();
    }

    @GetMapping("/boarding_passes/{boarding_passId}")
    public BoardingPass getBoardingPass(@PathVariable int boarding_passId){
        BoardingPass theBoardingPass = boardingPassService.findById(boarding_passId);
        if(theBoardingPass == null){
            throw new RuntimeException("Cargo id not found - " + boarding_passId);
        }
        return theBoardingPass;
    }

    @PostMapping("/boarding_passes")
    public BoardingPass addBoardingPass(@RequestBody PostBoardingPassDto postBoardingPassDto){
        return boardingPassService.save(postBoardingPassDto);
    }

    @PutMapping("/boarding_passes")
    public BoardingPass updateBoardingPass(@RequestBody PostBoardingPassDto postBoardingPassDto){
        return boardingPassService.save(postBoardingPassDto);
    }

    @DeleteMapping("/boarding_passes/{boarding_passId}")
    public String deleteBoardingPass(@PathVariable int boarding_passId){
        BoardingPass tempBoardingPass = boardingPassService.findById(boarding_passId);
        if(tempBoardingPass == null){
            throw new RuntimeException("Cargo id not found - " + boarding_passId);
        }
        boardingPassService.deleteById(boarding_passId);
        return "Deleted Cargo id - " + boarding_passId;
    }
}
