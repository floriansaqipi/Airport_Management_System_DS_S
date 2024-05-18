package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineMapper {
    public Airline postToAirline(PostAirlineDto postAirlineDto) {
        Airline airline = new Airline(
                postAirlineDto.code(),
                postAirlineDto.name()
        );
        airline.setAirlineId(0);

        return airline;
    }

    public Airline putToAirline(PutAirlineDto putAirlineDto) {
        Airline airline = new Airline(
                putAirlineDto.code(),
                putAirlineDto.name()
        );
        airline.setAirlineId(putAirlineDto.airlineId());

        return airline;
    }
}
