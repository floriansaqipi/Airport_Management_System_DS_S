package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAll();
    Passenger findById(int theId);

    Passenger findByUserEntityId(Integer userId);
    Passenger save(PutPassengerDto putPassengerDto);
    Passenger save(PostPassengerDto postPassengerDto);
    void deleteById(int theId);
}
