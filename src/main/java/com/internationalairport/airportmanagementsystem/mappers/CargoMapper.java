package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import org.springframework.stereotype.Service;

@Service
public class CargoMapper {
    public Cargo postToCargo(PostCargoDto postCargoDto){
        Cargo cargo = new Cargo(
                postCargoDto.weight(),
                postCargoDto.dimensions()
        );
        cargo.setCargoId(0);
        Flight flight = new Flight();
        flight.setFlightId(postCargoDto.flightId());

        cargo.setFlight(flight);
        return cargo;
    }

    public Cargo putToCargo(PutCargoDto putCargoDto){
        Cargo cargo = new Cargo(
                putCargoDto.weight(),
                putCargoDto.dimensions()
        );
        cargo.setCargoId(putCargoDto.cargoId());
        Flight flight = new Flight();
        flight.setFlightId(putCargoDto.flightId());

        cargo.setFlight(flight);
        return cargo;
    }
}
