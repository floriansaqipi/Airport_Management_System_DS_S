package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {
    FlightSchedule save(PostFlightScheduleDto postFlightScheduleDto);
    FlightSchedule save(PutFlightScheduleDto putFlightScheduleDto);
    FlightSchedule findById(Integer flightScheduleId);
    List<FlightSchedule> findAll();
    void deleteById(Integer flightScheduleId);
    String deleteAll();
}

