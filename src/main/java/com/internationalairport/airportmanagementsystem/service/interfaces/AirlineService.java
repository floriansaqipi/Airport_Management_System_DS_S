package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;

import java.util.List;

public interface AirlineService {
    Airline save(PostAirlineDto postAirlineDto);
    Airline save(PutAirlineDto putAirlineDto);
    Airline findById(Integer id);
    List<Airline> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
