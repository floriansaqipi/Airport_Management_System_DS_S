package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;

import java.util.List;

public interface FlightService {
    Flight save(PostFlightDto postFlightDto);
    Flight save(PutFlightDto putFlightDto);
    Flight findById(Integer id);
    List<Flight> findAll();
    void deleteById(Integer theId);
    String deleteAll();

}
