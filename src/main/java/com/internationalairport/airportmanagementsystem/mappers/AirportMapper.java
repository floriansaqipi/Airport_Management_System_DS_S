package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import org.springframework.stereotype.Service;

@Service
public class AirportMapper {
    public Airport postToAirport(PostAirportDto postAirportDto) {
        Airport airport = new Airport(
                postAirportDto.code(),
                postAirportDto.name(),
                postAirportDto.locationCity(),
                postAirportDto.locationCountry()
        );
        airport.setAirportId(0);

        return airport;
    }

    public Airport putToAirport(PutAirportDto putAirportDto) {
        Airport airport = new Airport(
                putAirportDto.code(),
                putAirportDto.name(),
                putAirportDto.locationCity(),
                putAirportDto.locationCountry()
        );
        airport.setAirportId(putAirportDto.airportId());

        return airport;
    }
}
