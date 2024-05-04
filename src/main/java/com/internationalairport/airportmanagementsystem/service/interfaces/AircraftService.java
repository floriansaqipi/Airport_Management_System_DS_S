package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;

import java.util.List;

public interface AircraftService {
    Aircraft save(PostAircraftDto postAircraftDto);
    Aircraft save(PutAircraftDto putAircraftDto);
    Aircraft findById(Integer id);
    List<Aircraft> findAll();
    void deleteById(Integer theId);
    String deleteAll();

}
