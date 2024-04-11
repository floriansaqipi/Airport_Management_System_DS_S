package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.FlightCrew;

import java.util.List;

public interface FlightCrewService {
    FlightCrew save(FlightCrew flightCrew);
    FlightCrew findById(Integer flightCrewId);
    List<FlightCrew> findAll();
    void deleteById(Integer flightCrewId);
    String deleteAll();
}
