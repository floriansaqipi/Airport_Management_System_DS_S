package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.AirportService;

import java.util.List;

public interface AirportServiceService {
    AirportService save(AirportService theAirportService);
    AirportService findById(Integer id);
    List<AirportService> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
