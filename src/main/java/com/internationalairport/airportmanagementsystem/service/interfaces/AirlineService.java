package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Airline;

import java.util.List;

public interface AirlineService {
    Airline save(Airline theAirline);
    Airline findById(Integer id);
    List<Airline> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
