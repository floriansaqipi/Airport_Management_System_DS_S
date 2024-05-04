package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;

import java.util.List;

public interface ParkingService {
    Parking save(PostParkingDto postParkingDto);
    Parking save(PutParkingDto putParkingDto);
    Parking findById(Integer id);
    List<Parking> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
