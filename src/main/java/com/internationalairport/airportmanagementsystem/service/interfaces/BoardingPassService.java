package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;

import java.util.List;

public interface BoardingPassService {
    BoardingPass save(PostBoardingPassDto postBoardingPassDto);
    BoardingPass save(PutBoardingPassDto putBoardingPassDto);
    BoardingPass findById(Integer theId);
    List<BoardingPass> findAll();
    void deleteById(Integer theId);
}
