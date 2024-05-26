package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassengerMapper {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PassengerMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    public Passenger postToPassenger(PostPassengerDto postPassengerDto) {


        Passenger passenger = new Passenger(
                postPassengerDto.name(),
                postPassengerDto.passportNumber(),
                postPassengerDto.nationality(),
                postPassengerDto.contactDetails()
        );
        passenger.setPassengerId(0);
        UserEntity user= new UserEntity(postPassengerDto.username(),
                passwordEncoder.encode(postPassengerDto.password()));
        passenger.setUserEntity(user);

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

        UserEntity user= new UserEntity(putPassengerDto.username(),
                passwordEncoder.encode(putPassengerDto.password()));
        passenger.setUserEntity(user);
        return passenger;
    }

}
