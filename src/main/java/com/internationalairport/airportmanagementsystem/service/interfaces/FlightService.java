package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;

import java.util.List;

public interface FlightService {
    Flight save(PostFlightDto postFlightDto);
    Flight findById(Integer id);
    List<Flight> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
