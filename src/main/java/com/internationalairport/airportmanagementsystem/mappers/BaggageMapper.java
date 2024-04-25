package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class BaggageMapper {

    public Baggage toBaggage(PostBaggageDto postBaggageDto){
        Baggage baggage = new Baggage(
                postBaggageDto.weight()
        );
        Passenger passenger = new Passenger();
        passenger.setId(postBaggageDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(postBaggageDto.flightId());

        baggage.setPassenger(passenger);
        baggage.setFlight(flight);
        return baggage;
    }
}
