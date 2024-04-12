package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.RentalService;

import java.util.List;

public interface RentalServiceService {
    RentalService save(RentalService theRentalService);
    RentalService findById(Integer id);
    List<RentalService> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
