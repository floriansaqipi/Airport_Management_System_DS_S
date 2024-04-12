package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAll();

    Passenger findById(int theId);

    Passenger save(Passenger thePassenger);

    void deleteById(int theId);
}
