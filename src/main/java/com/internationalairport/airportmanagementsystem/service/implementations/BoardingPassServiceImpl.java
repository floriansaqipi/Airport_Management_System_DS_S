package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.BoardingPassRepository;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.service.interfaces.BoardingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardingPassServiceImpl implements BoardingPassService {

    private BoardingPassRepository boardingPassRepository;

    @Autowired
    public BoardingPassServiceImpl(BoardingPassRepository theBoardingPassRepository){
        boardingPassRepository = theBoardingPassRepository;
    }
    @Override
    public BoardingPass save(BoardingPass theBoardingPass) {
        return boardingPassRepository.save(theBoardingPass);
    }

    @Override
    public BoardingPass findById(Integer theId) {
        Optional<BoardingPass> result = boardingPassRepository.findById(theId);
        BoardingPass theBoardingPass = null;
        if (result.isPresent()) {
            theBoardingPass = result.get();
        }
        else {
            throw new RuntimeException("Did not find Boarding Pass id - " + theId);
        }
        return theBoardingPass;
    }

    @Override
    public List<BoardingPass> findAll() {
        return boardingPassRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        boardingPassRepository.deleteById(theId);
    }
}
