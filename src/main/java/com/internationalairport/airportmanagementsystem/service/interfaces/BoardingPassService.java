package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Cargo;

import java.util.List;

public interface BoardingPassService {
    BoardingPass save(BoardingPass theBoardingPass);
    BoardingPass findById(Integer theId);
    List<BoardingPass> findAll();
    void deleteById(Integer theId);
}
