package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;

import java.util.List;

public interface AirportServiceService {
    AirportService save(PostAirportServiceDto postAirportServiceDto);
    AirportService save(PutAirportServiceDto putAirportServiceDto);
    AirportService findById(Integer id);
    List<AirportService> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
