package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCheckInDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCheckInDto;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class CheckInMapper {

    public CheckIn postToCheckIn(PostCheckInDto postCheckInDto){
        CheckIn checkIn = new CheckIn(
                postCheckInDto.checkInTime(),
                postCheckInDto.deskNumber()
        );
        checkIn.setCheckInId(0);
        if(postCheckInDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setId(postCheckInDto.passengerId());
            checkIn.setPassenger(passenger);
        }

        if(postCheckInDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(postCheckInDto.flightId());
            checkIn.setFlight(flight);
        }
        return checkIn;
    }

    public CheckIn putToCheckIn(PutCheckInDto putCheckInDto){
        CheckIn checkIn = new CheckIn(
                putCheckInDto.checkInTime(),
                putCheckInDto.deskNumber()
        );
        checkIn.setCheckInId(putCheckInDto.checkInId());

        if(putCheckInDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setId(putCheckInDto.passengerId());
            checkIn.setPassenger(passenger);
        }

        if(putCheckInDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(putCheckInDto.flightId());
            checkIn.setFlight(flight);
        }

        return checkIn;
    }
}
