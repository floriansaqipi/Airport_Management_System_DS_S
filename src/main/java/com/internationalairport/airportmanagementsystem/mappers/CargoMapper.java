package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import org.springframework.stereotype.Service;

@Service
public class CargoMapper {

    public Cargo toCargo(PostCargoDto postCargoDto){
        Cargo cargo = new Cargo(
                postCargoDto.weight(),
                postCargoDto.dimensions()
        );
        Flight flight = new Flight();
        flight.setFlightId(postCargoDto.flightId());

        cargo.setFlight(flight);
        return cargo;
    }
}
