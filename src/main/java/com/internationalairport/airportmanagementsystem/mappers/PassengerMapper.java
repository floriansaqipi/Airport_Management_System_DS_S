package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class PassengerMapper {
    public Passenger postToPassenger(PostPassengerDto postPassengerDto) {
        Passenger passenger = new Passenger(
                postPassengerDto.name(),
                postPassengerDto.passportNumber(),
                postPassengerDto.nationality(),
                postPassengerDto.contactDetails()
        );
        passenger.setPassengerId(0);
        return passenger;
    }

    public Passenger putToPassenger(PutPassengerDto putPassengerDto) {
        Passenger passenger = new Passenger(
                putPassengerDto.name(),
                putPassengerDto.passportNumber(),
                putPassengerDto.nationality(),
                putPassengerDto.contactDetails()
        );
        passenger.setPassengerId(putPassengerDto.passengerId());
        return passenger;
    }
}
