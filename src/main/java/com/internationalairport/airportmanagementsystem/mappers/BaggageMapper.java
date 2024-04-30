package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class BaggageMapper {

    public Baggage postToBaggage(PostBaggageDto postBaggageDto){
        Baggage baggage = new Baggage(
                postBaggageDto.weight()
        );
        baggage.setBaggageId(0);
        Passenger passenger = new Passenger();
        passenger.setId(postBaggageDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(postBaggageDto.flightId());

        baggage.setPassenger(passenger);
        baggage.setFlight(flight);
        return baggage;
    }

    public Baggage putToBaggage(PutBaggageDto putBaggageDto){
        Baggage baggage = new Baggage(
                putBaggageDto.weight()
        );
        baggage.setBaggageId(putBaggageDto.baggageId());
        Passenger passenger = new Passenger();
        passenger.setId(putBaggageDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(putBaggageDto.flightId());

        baggage.setPassenger(passenger);
        baggage.setFlight(flight);
        return baggage;
    }
}
