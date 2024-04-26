package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import org.springframework.stereotype.Service;

@Service
public class AircraftMapper {

    public Aircraft toAircraft(PostAircraftDto postAircraftDto) {
        Aircraft aircraft = new Aircraft(
                postAircraftDto.tailNumber(),
                postAircraftDto.model(),
                postAircraftDto.capacity()
        );
        Airline airline = new Airline();
        airline.setAirlineId(postAircraftDto.airlineId());

        aircraft.setAirline(airline);
        return aircraft;
    }
}