package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;

import java.util.List;

public interface AircraftService {
    Aircraft save(PostAircraftDto postAircraftDto);
    Aircraft findById(Integer id);
    List<Aircraft> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
