package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;

import java.util.List;

public interface BaggageService {
    Baggage save(PostBaggageDto postBaggageDto);
    Baggage save(PutBaggageDto putBaggageDto);
    Baggage findById(Integer theId);

    List<Baggage> findAll();

    List<Baggage> findByPassengerId(Integer passengerId);
    void deleteById(Integer theId);
}
