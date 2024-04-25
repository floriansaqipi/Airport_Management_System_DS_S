package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;

import java.util.List;

public interface BaggageService {
    Baggage save(PostBaggageDto postBaggageDto);
    Baggage findById(Integer theId);
    List<Baggage> findAll();
    void deleteById(Integer theId);
}
