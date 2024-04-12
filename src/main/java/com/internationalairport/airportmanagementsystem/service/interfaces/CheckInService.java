package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.CheckIn;

import java.util.List;

public interface CheckInService {
    CheckIn save(CheckIn theCheckIn);
    CheckIn findById(Integer id);
    List<CheckIn> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
