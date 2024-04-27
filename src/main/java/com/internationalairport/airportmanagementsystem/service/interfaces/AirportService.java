package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;

import java.util.List;

public interface AirportService {
    Airport save(PostAirportDto postAirportDto);
    Airport findById(Integer id);
    List<Airport> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
