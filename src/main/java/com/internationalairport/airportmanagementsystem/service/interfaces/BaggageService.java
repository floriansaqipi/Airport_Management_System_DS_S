package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Cargo;

import java.util.List;

public interface BaggageService {
    Baggage save(Baggage theBaggage);
    Baggage findById(Integer theId);
    List<Baggage> findAll();
    void deleteById(Integer theId);
}
