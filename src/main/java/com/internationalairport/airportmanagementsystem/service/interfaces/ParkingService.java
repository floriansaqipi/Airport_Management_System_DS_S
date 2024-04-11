package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Parking;

import java.util.List;

public interface ParkingService {
    Parking save(Parking theParking);
    Parking findById(Integer id);
    List<Parking> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
