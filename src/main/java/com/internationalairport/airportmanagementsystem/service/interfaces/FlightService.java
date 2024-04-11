package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Flight;

import java.util.List;

public interface FlightService {
    Flight save(Flight theFlight);
    Flight findById(Integer id);
    List<Flight> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
