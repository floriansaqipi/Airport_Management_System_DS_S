package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineMapper {
    public Airline toAirline(PostAirlineDto postAirlineDto) {
        Airline airline = new Airline(
                postAirlineDto.code(),
                postAirlineDto.name()
        );
        List<Aircraft> aircrafts = new ArrayList<>();
        postAirlineDto.aircrafts().forEach(aircraftId -> {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(aircraftId);
            aircrafts.add(aircraft);
        });
        airline.setAircrafts(aircrafts);
        return airline;
    }
}
