package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;

import java.util.List;

public interface FlightCrewService {
    Flight save(PostFlightCrewDto postFlightCrewDto);
    void deleteByFlightIdAndEmployeeId(Integer flightId, Integer employeeId);
}

