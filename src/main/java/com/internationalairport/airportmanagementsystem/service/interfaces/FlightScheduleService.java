package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {
    FlightSchedule save(FlightSchedule theFlightSchedule);
    FlightSchedule findById(Integer flightScheduleId);
    List<FlightSchedule> findAll();
    void deleteById(Integer flightScheduleId);
    String deleteAll();
}

