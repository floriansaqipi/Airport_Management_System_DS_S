package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import org.springframework.stereotype.Service;

@Service
public class ParkingMapper {
    public Parking postToParking(PostParkingDto postParkingDto) {
        Parking Parking = new Parking(
                postParkingDto.location(),
                postParkingDto.capacity(),
                postParkingDto.rate()
        );
        Parking.setParkingId(0);

        return Parking;
    }

    public Parking putToParking(PutParkingDto putParkingDto) {
        Parking Parking = new Parking(
                putParkingDto.location(),
                putParkingDto.capacity(),
                putParkingDto.rate()
        );
        Parking.setParkingId(putParkingDto.parkingId());

        return Parking;
    }
}